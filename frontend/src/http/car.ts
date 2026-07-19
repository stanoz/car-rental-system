import axios from "axios";
import type { CarDto } from "../model/car";

const BASE_URL: string = import.meta.env.VITE_CAR_RENTAL_SYSTEM_BASE_URL;

export async function getAllCars(): Promise<CarDto[]> {
    return (await axios.get(`${BASE_URL}/cars`)).data;
}