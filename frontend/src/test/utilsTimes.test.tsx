import { describe, it, expect } from "vitest";
import { formatDate, formatTime } from "../utils/times";

describe("formatDate tests", () => {
    it("formats single-digit month and day", () => {
        expect(formatDate(new Date(2026, 0, 5))).toBe("2026-01-05");
    });

    it("formats double-digit month and day", () => {
        expect(formatDate(new Date(2026, 10, 15))).toBe("2026-11-15");
    });
});

describe("formatTime tests", () => {
    it("adds leading zeros to hours and minutes", () => {
        expect(formatTime(new Date(2026, 0, 1, 9, 5))).toBe("09:05");
    });

    it("formats afternoon time", () => {
        expect(formatTime(new Date(2026, 0, 1, 15, 45))).toBe("15:45");
    });
});
