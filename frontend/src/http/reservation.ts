import axios from "axios";
import type { ConfirmedReservationDto, CreateReservationDto } from "../model/reservation";

const BASE_URL: string = import.meta.env.VITE_CAR_RENTAL_SYSTEM_BASE_URL;

export async function createReservation(createReservationDto: CreateReservationDto): Promise<number> {
    return (await axios.post(`${BASE_URL}/reservations`, {
        ...createReservationDto
    })).data;
}

export async function getReservationById(id: number): Promise<ConfirmedReservationDto> {
    return (await axios.get(`${BASE_URL}/reservations/${id}`)).data;
}
