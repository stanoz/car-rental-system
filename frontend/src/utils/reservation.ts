export function calculateReservationCost(
    start: Date,
    end: Date,
    carCostPerDay: number
): number {
    const durationInHours =
        (end.getTime() - start.getTime()) / (1000 * 60 * 60);

    const durationInDays = Number((durationInHours / 24).toFixed(2));

    return durationInDays * carCostPerDay;
}