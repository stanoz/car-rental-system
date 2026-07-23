import { describe, expect, it } from "vitest";
import { validateReservationDates } from "../utils/reservationValidation";

describe("validateReservationDates tests", () => {
    it("rejects start date after end date", () => {
        const result = validateReservationDates(
            new Date("2026-07-10T10:00"),
            new Date("2026-07-09T10:00")
        );

        expect(result.valid).toBe(false);
        expect(result.message).toBe(
            "Start date cannot be after the End date."
        );
    });

    it("rejects identical start and end dates", () => {
        const date = new Date("2026-07-10T10:00");

        const result = validateReservationDates(date, date);

        expect(result.valid).toBe(false);
        expect(result.message).toBe(
            "Start date and End date cannot be exactly the same."
        );
    });

    it("accepts a valid reservation period", () => {
        const result = validateReservationDates(
            new Date("2026-07-10T10:00"),
            new Date("2026-07-11T10:00")
        );

        expect(result).toEqual({
            valid: true,
            message: "",
        });
    });
});
