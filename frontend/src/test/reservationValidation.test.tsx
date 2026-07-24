import {
  afterEach,
  beforeEach,
  describe,
  expect,
  it,
  vi,
} from "vitest";
import { validateReservationDates } from "../utils/reservationValidation";

beforeEach(() => {
  vi.useFakeTimers();
  vi.setSystemTime(new Date("2026-07-20T12:00:00"));
});

afterEach(() => {
  vi.useRealTimers();
});

describe("validateReservationDates tests", () => {
    it("rejects start date after end date", () => {
        const end = new Date();
        const start = new Date(end);
        start.setDate(end.getDate() + 1);
        const result = validateReservationDates(start, end);

        expect(result.valid).toBe(false);
        expect(result.message).toBe(
            "Start date cannot be after the End date."
        );
    });

    it("rejects start date before now", () => {
        const yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);

        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);

        const result = validateReservationDates(yesterday, tomorrow);

        expect(result.valid).toBe(false);
        expect(result.message).toBe(
            "Start date cannot be in the past."
        );
    });

    it("rejects identical start and end dates", () => {
        const start = new Date();
        start.setDate(start.getDate() + 1);

        const end = new Date(start);

        const result = validateReservationDates(start, end);

        expect(result.valid).toBe(false);
        expect(result.message).toBe(
            "Start date and End date cannot be exactly the same."
        );
    });

    it("accepts a valid reservation period", () => {
        const start = new Date();
        start.setDate(start.getDate() + 1);

        const end = new Date(start);
        end.setDate(end.getDate() + 2);

        const result = validateReservationDates(start, end);

        expect(result).toEqual({
            valid: true,
            message: "",
        });
    });
});
