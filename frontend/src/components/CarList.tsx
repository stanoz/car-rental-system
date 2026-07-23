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
                <>
                    <h3 className="text-center text-3xl text-cyan-50">Select a car</h3>
                    {data.length > 0 ? (
                        <ul className="flex flex-col space-y-8 mt-10">
                            {data.map(car => (
                                <li key={car.id}><Car car={car} /></li>
                            ))}
                        </ul>
                    ) : (
                        <p className="text-center text-2xl text-cyan-50">There are no available cars.</p>
                    )}
                </>
            )}
        </>
    )
}