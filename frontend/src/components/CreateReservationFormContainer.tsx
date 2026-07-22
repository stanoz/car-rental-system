import { useState } from "react";
import Steps from "./Steps";
import { Link, useNavigate } from "react-router";
import CarConfirmation from "./CarConfirmation";
import ReservationDateTimePicker from "./ReservationDateTimePicker";
import useSelectorTyped from "../hooks/useSelectorTyped";
import UserData from "./UserData";
import ReservationSummary from "./ReservationSummary";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { createReservation } from "../http/reservation";
import useDispatchTyped from "../hooks/useDispatchTyped";
import { carActions } from "../redux/car";
import { createReservationActions } from "../redux/create-reservation";
import LoadingSpinner from "./LoadingSpinner";
import { reservationConfirmationActions } from "../redux/reservation-confirmation";

const TOTAL_STEPS = 4;
const commonClasses = "px-4 py-2 rounded-md hover:cursor-pointer";

export default function CreateReservationFormContainer() {
    const [stepNumber, setStepNumber] = useState<number>(1)
    const [errorMessage, setErrorMessage] = useState("");

    const { isStepCompleted, dates, user } = useSelectorTyped(state => state.createReservation);
    const { car } = useSelectorTyped(state => state.car);

    const queryClient = useQueryClient();
    const dispatch = useDispatchTyped();
    const navigate = useNavigate();

    const { isPending, mutateAsync } = useMutation({
        mutationKey: ["create-reservation", car, dates, user],
        mutationFn: () => createReservation({
            carId: car.id,
            startDateTime: `${dates.fromDate}T${dates.fromTime}:00`,
            endDateTime: `${dates.toDate}T${dates.toTime}:00`,
            user
        }),
        onError: (error) => {
            setErrorMessage(error.message ?? "Something went wrong. Please try again later.");
        },
        onSuccess: async () => {
            await queryClient.invalidateQueries({ queryKey: ['all-cars'] });
            dispatch(carActions.setCarStateToDefault());
            dispatch(createReservationActions.setDatesToDefault());
            dispatch(createReservationActions.setUserDataToDefault());
            navigate("/reservation-confirmation");
        }
    });

    const handleCreateReservation = async () => {
        const reservationId = await mutateAsync();
        dispatch(reservationConfirmationActions.setReservationId(reservationId));
    }

    const handleClickNextStep = () => {
        setStepNumber(prevState => Math.min(prevState + 1, TOTAL_STEPS));
    }

    const handleClickPreviousStep = () => {
        setStepNumber(prevState => Math.max(prevState - 1, 1));
    }

    return (
        <div className="flex flex-col items-center">
            <Steps stepNumber={stepNumber} />
            {stepNumber === 1 && <CarConfirmation />}
            {stepNumber === 2 && <ReservationDateTimePicker />}
            {stepNumber === 3 && <UserData />}
            {stepNumber === 4 && <ReservationSummary />}
            {!isPending && (<div className="flex w-[28rem] text-2xl text-cyan-50 justify-between">
                {stepNumber === 1 && <Link to="/" className={`ml-0 mr-auto bg-blue-800 hover:bg-blue-900 ${commonClasses}`}>Return to Cars List</Link >}
                {stepNumber > 1 && <button type="button" onClick={handleClickPreviousStep} className={`ml-0 mr-auto bg-blue-800 hover:bg-blue-900 ${commonClasses}`}>Previous Step</button>}
                {stepNumber < 4 && <button disabled={!isStepCompleted} type="button" onClick={handleClickNextStep} className={`mr-0 ml-auto ${isStepCompleted ? "bg-green-700 hover:bg-green-800" : "bg-red-300"}  ${commonClasses}`}>Next Step</button>}
                {stepNumber == 4 && <button type="button" onClick={handleCreateReservation} className={`mr-0 ml-auto bg-green-700 hover:bg-green-800 ${commonClasses}`}>Create Reservation</button>}
            </div>)}
            {isPending && <LoadingSpinner />}
            {errorMessage && <p className="text-red-600 text-xl">{errorMessage}</p>}
        </div>
    );
}
