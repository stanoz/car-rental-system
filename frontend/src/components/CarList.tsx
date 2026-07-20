import { useQuery } from "@tanstack/react-query"
import { getAllCars } from "../http/car";
import LoadingSpinner from "./LoadingSpinner";
import Car from "./Car";

export default function CarList() {
    const { data, isError, isSuccess, isLoading } = useQuery({
        queryFn: getAllCars,
        queryKey: ['all-cars']
    });
    return (
        <>
            {isError && <p className="text-2xl text-red-700">Failed to fetch cars!</p>}
            {isLoading && <LoadingSpinner />}
            {isSuccess && (
                <ul className="flex flex-col space-y-8 mt-10">
                    {data.map(car => (
                        <li key={car.id}><Car car={car} /></li>
                    ))}
                </ul>
            )}
        </>
    )
}