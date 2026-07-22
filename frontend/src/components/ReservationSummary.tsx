import useSelectorTyped from "../hooks/useSelectorTyped";
import { priceFormatter } from "../utils/currency";

export default function ReservationSummary() {
    const { dates, user } = useSelectorTyped(state => state.createReservation);
    const { car } = useSelectorTyped(state => state.car);

    return (
        <div className="flex flex-col items-center text-cyan-50 text-xl">
            <h2 className="text-3xl">Reservation data summary</h2>
            <main>
                <section className="flex flex-col items-center">
                    <h3 className="text-2xl">Car</h3>
                    <div className="flex flex-col items-start">
                        <span>Brand: {car.brand}</span>
                        <span>Type: {car.type}</span>
                        <span>Cost per Day: {priceFormatter.format(car.costPerDay)}</span>
                        <span>Available: {car.inStock}</span>
                    </div>
                </section>
                <section className="flex flex-col items-center">
                    <h3 className="text-2xl">Selected Dates</h3>
                    <h4>From:</h4>
                    <div className="flex">
                        <span>Date: {dates.fromDate}</span>
                        <span>Time: {dates.fromTime}</span>
                    </div>
                    <h4>To:</h4>
                    <div className="flex">
                        <span>Date: {dates.toDate}</span>
                        <span>Time: {dates.toTime}</span>
                    </div>
                </section>
                <section className="flex flex-col items-center">
                    <h3 className="text-2xl">Your data</h3>
                    <div className="flex flex-col items-start">
                        <span>Frist name: {user.firstName}</span>
                        <span>Last name: {user.lastName}</span>
                        <span>Email address: {user.emailAddress}</span>
                        <span>Phone number: {user.phoneNumber}</span>
                        <span>Driving License ID: {user.drivingLicenseId}</span>
                    </div>
                </section>
            </main>
        </div>
    );
}