import type { CarDto } from "../model/car";

type CarProps = {
    car: CarDto
}

export default function Car({ car }: CarProps) {
    return (
        <div className="flex items-center text-2xl border-cyan-200 border-2 rounded-md px-4 py-3 text-center text-sky-100 space-x-10 hover:bg-slate-800 hover:cursor-pointer hover:text-sky-200 hover:border-cyan-300">
            <div className="flex flex-col items-start">
                <span>Car type: {car.type}</span>
                <span>Brand: {car.brand}</span>
            </div>
            <div className="flex flex-col items-start">
                <span>Cost per day: {car.costPerDay}</span>
                <span>Available: {car.inStock}</span>
            </div>
        </div>
    );
}