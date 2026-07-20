import type { IconType } from "react-icons";

type StepProps = {
  isActive: boolean;
  icon: IconType;
};

export default function Step({ icon: Icon, isActive }: StepProps) {
  return (
    <Icon
      className={
        isActive
          ? "text-blue-600 fill-blue-600"
          : "text-gray-400 fill-gray-400"
      }
    />
  );
}