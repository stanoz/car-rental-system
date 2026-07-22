import type { ConfirmationCarDto } from "./car"
import type { PaymentStatus } from "./enum/payment-status"

export type CreateReservationDates = {
    fromDate: string,
    fromTime: string,
    toDate: string,
    toTime: string,
}

export type CreateReservationUserData = {
    firstName: string,
    lastName: string,
    emailAddress: string,
    phoneNumber: string,
    drivingLicenseId: string,
}

export type CreateReservationDto = {
    carId: number,
    user: CreateReservationUserData,
    startDateTime: string,
    endDateTime: string
}

export type ConfirmedReservationDto = {
    id: number,
     startDateTime: string,
    endDateTime: string,
    totalCost: number,
    paymentStatus: PaymentStatus,
    user: CreateReservationUserData,
    car: ConfirmationCarDto
}
