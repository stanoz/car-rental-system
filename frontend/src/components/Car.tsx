import { useNavigate } from "react-router";
import type { CarDto } from "../model/car";
import useDispatchTyped from "../hooks/useDispatchTyped";
import { carActions } from "../redux/car";
import { priceFormatter } from "../utils/currency";

type CarProps = {
    car: CarDto
}

export default function Car({ car }: CarProps) {

    const navigate = useNavigate();

    const dispatch = useDispatchTyped();

    const handleOnClickCar = () => {
        dispatch(carActions.setCar(car));
        navigate("/create-reservation");
    }

    return (
        <div onClick={handleOnClickCar} className="flex items-center text-2xl border-cyan-200 border-2 rounded-md px-4 py-3 text-center text-sky-100 space-x-10 hover:bg-slate-800 hover:cursor-pointer hover:text-sky-200 hover:border-cyan-300">
            <div className="flex flex-col items-start">
                <span>Car type: {car.type}</span>
                <span>Brand: {car.brand}</span>
            </div>
            <div className="flex flex-col items-start">
                <span>Cost per day: {priceFormatter.format(car.costPerDay)}</span>
                <span>Available: {car.inStock}</span>
            </div>
        </div>
    );
}