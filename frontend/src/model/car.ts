import type { CarCategory } from "./enum/car-category"

export type CarDto = {
    id: number,
    brand: string,
    inStock: number,
    costPerDay: number,
    type: CarCategory
}

export type ConfirmationCarDto = {
    brand: string,
    costPerDay: number,
    type: CarCategory,
    licensePlate: string
}
