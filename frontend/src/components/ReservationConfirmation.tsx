import { useQuery } from "@tanstack/react-query";
import useSelectorTyped from "../hooks/useSelectorTyped";
import { getReservationById } from "../http/reservation";
import LoadingSpinner from "./LoadingSpinner";
import { priceFormatter } from "../utils/currency";
import { formatDate, formatTime } from "../utils/times";
import { useNavigate } from "react-router";

export default function ReservationConfirmation() {
    const { id } = useSelectorTyped(state => state.reservationConfirmation);
    const { isLoading, isError, error, data: reservation, isSuccess } = useQuery({
        queryKey: ['reservation-confirmation', id],
        queryFn: () => getReservationById(id)
    });

    const navigate = useNavigate();

    const handleBackToCarList = () => {
        navigate("/");
    }

    return (
        <div className="text-cyan-50 flex flex-col items-center">
            <h2 className="text-3xl my-8">Reservation Confirmation</h2>
            {isLoading && <LoadingSpinner />}
            <div className="h-12 mx-auto">
                {isError && <p className="text-xl text-red-600">{error?.message ?? "Something went wrong."}</p>}
            </div>
            {isSuccess && reservation && (
                <main className="mt-6 flex flex-col items-center space-y-6">
                    <p className="text-2xl font-bolder">Reservation ID: {reservation.id}</p>
                    <p className="text-2xl font-bolder">Total cost {priceFormatter.format(reservation.totalCost)}</p>
                    <section className="flex flex-col items-center">
                        <h3 className="text-2xl mb-2">Car</h3>
                        <div className="flex flex-col items-start space-y-1">
                            <span>Brand: {reservation.car.brand}</span>
                            <span>Type: {reservation.car.type}</span>
                            <span>Cost per Day: {priceFormatter.format(reservation.car.costPerDay)}</span>
                            <span>License plate: {reservation.car.licensePlate}</span>
                        </div>
                    </section>
                    <section className="flex flex-col items-center">
                        <h3 className="text-2xl mb-2">Selected Dates</h3>
                        <h4>From:</h4>
                        <div className="flex mt-1 space-x-6">
                            <span>Date: {formatDate(new Date(reservation.startDateTime))}</span>
                            <span>Time: {formatTime(new Date(reservation.startDateTime))}</span>
                        </div>
                        <h4>To:</h4>
                        <div className="flex mt-1 space-x-6">
                            <span>Date: {formatDate(new Date(reservation.endDateTime))}</span>
                            <span>Time: {formatTime(new Date(reservation.endDateTime))}</span>
                        </div>
                    </section>
                    <section className="flex flex-col items-center">
                        <h3 className="text-2xl mb-2">Your data</h3>
                        <div className="flex flex-col items-start space-y-1">
                            <span>Frist name: {reservation.user.firstName}</span>
                            <span>Last name: {reservation.user.lastName}</span>
                            <span>Email address: {reservation.user.emailAddress}</span>
                            <span>Phone number: {reservation.user.phoneNumber}</span>
                            <span>Driving License ID: {reservation.user.drivingLicenseId}</span>
                        </div>
                    </section>
                </main>
            )}
            <button
                type="button"
                onClick={handleBackToCarList}
                className="mt-8 mx-auto bg-blue-800 hover:bg-blue-900 px-4 py-2 rounded-md hover:cursor-pointer">
                Return to cars list
            </button>
        </div>
    );
}