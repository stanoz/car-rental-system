import { useEffect } from "react";
import useSelectorTyped from "../hooks/useSelectorTyped";
import { priceFormatter } from "../utils/currency";
import useDispatchTyped from "../hooks/useDispatchTyped";
import { createReservationActions } from "../redux/create-reservation";

export default function CarConfirmation() {
    const car = useSelectorTyped(state => state.car.car);

    const dispatch = useDispatchTyped();

    useEffect(() => {
        dispatch(createReservationActions.completeStep());
    }, []);

    return (
        <div className="flex flex-col items-center text-cyan-50 mt-18">
            <h3 className="text-center text-3xl">Confirm selected car</h3>
            <main className="flex flex-col items-start text-2xl space-y-8 my-18">
                <span>Brand: {car.brand}</span>
                <span>Type: {car.type}</span>
                <span>Cost per Day: {priceFormatter.format(car.costPerDay)}</span>
                <span>Available: {car.inStock}</span>
            </main>
        </div>
    );
}