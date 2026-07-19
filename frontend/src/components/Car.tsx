import type { CarDto } from "../model/car";

type CarProps = {
    car: CarDto
}

export default function Car({ car }: CarProps) {
    return (
        <div className="flex items-center text-2xl border-cyan-200 border-2 rounded-xs px-4 py-3 text-center text-sky-100 space-y-4">
            <span>{car.type}</span>
            <span>{car.brand}</span>
            <span>{car.costPerDay}</span>
            <span>{car.inStock}</span>
        </div>
    );
}