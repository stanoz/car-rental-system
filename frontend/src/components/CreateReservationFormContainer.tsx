import { useState } from "react";
import Steps from "./Steps";
import { Link } from "react-router";
import CarConfirmation from "./CarConfirmation";
import ReservationDateTimePicker from "./ReservationDateTimePicker";
import useSelectorTyped from "../hooks/useSelectorTyped";
import UserData from "./UserData";
import ReservationSummary from "./ReservationSummary";

const TOTAL_STEPS = 4;
const commonClasses = "px-4 py-2 rounded-md hover:cursor-pointer";

export default function CreateReservationFormContainer() {
    const [stepNumber, setStepNumber] = useState<number>(1)

    const { isStepCompleted } = useSelectorTyped(state => state.createReservation);

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
            <div className="flex w-96 text-2xl text-cyan-50">
                {stepNumber === 1 && <Link to="/" className={`ml-0 mr-auto bg-blue-800 hover:bg-blue-900 ${commonClasses}`}>Return to Cars List</Link >}
                {stepNumber > 1 && <button type="button" onClick={handleClickPreviousStep} className={`ml-0 mr-auto bg-blue-800 hover:bg-blue-900 ${commonClasses}`}>Previous Step</button>}
                {stepNumber < 4 && <button disabled={!isStepCompleted} type="button" onClick={handleClickNextStep} className={`mr-0 ml-auto ${isStepCompleted ? "bg-green-700 hover:bg-green-800" : "bg-red-300"}  ${commonClasses}`}>Next Step</button>}
            </div>
        </div>
    );
}
