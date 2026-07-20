import type { IconType } from "react-icons";

type StepProps = {
    isActive: boolean;
    icon: IconType;
};

export default function Step({ icon: Icon, isActive }: StepProps) {
    return (
        <Icon
            size={40}
            className={
                isActive
                    ? "text-emerald-600"
                    : "text-gray-400"
            }
        />
    );
}