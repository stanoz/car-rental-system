export type ValidationResult = {
    valid: boolean;
    message: string;
};

export function validateReservationDates(
    start: Date,
    end: Date
): ValidationResult {
    const now = new Date();

    if (start < now) {
        return {
            valid: false,
            message: "Start date cannot be in the past.",
        };
    }

    if (start > end) {
        return {
            valid: false,
            message: "Start date cannot be after the End date.",
        };
    }

    if (start.getTime() === end.getTime()) {
        return {
            valid: false,
            message: "Start date and End date cannot be exactly the same.",
        };
    }

    return {
        valid: true,
        message: "",
    };
}