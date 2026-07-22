import z from "zod";

export const userDataSchema = z.object({
    firstName: z
        .string()
        .trim()
        .min(1, "First name cannot be empty.")
        .max(255, "Maximum length is 255 characters."),
    lastName: z
        .string()
        .trim()
        .min(1, "Last name cannot be empty.")
        .max(255, "Maximum length is 255 characters."),
    emailAddress: z
        .email("Email address is invalid."),
    phoneNumber: z
        .string()
        .regex(/^\d{9}$/, "Phone number must contain exactly 9 digits"),
    drivingLicenseId: z
        .string()
        .trim()
        .min(1, "Driving license ID name cannot be empty.")
        .max(255, "Maximum length is 255 characters."),
});