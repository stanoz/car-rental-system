import { useQuery } from "@tanstack/react-query"
import { getAllCars } from "../http/car";
import LoadingSpinner from "./LoadingSpinner";

export default function CarList() {
    const { data, isError, isSuccess, isLoading } = useQuery({
        queryFn: getAllCars,
        queryKey: ['all-cars']
    });
    return (
        <>
            {isError && <p className="text-2xl text-red-700">Failed to fetch cars!</p>}
            {isLoading && <LoadingSpinner />}
            {isSuccess && <p>cars list</p>}
        </>
    )
}