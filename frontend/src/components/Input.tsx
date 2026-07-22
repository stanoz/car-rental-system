type InputProps = {
    id: string,
    value: string,
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    errorMessage: string,
    label: string,
    name: string,
    type?: string
}

export default function Input({ value, onChange, errorMessage, label, id, name, type }: InputProps) {
    return (
        <div className="flex flex-col items-start text-cyan-50 text-xl w-80 mb-6">
            <label htmlFor={id} className="mb-2">{label}</label>
            <input
                id={id}
                name={name}
                type={type ?? "text"}
                required value={value}
                onChange={onChange}
                className="border-cyan-50 border-1 rounded w-full"
            />
            <div className="h-12 mt-2">
                <p className="text-red-600 text-xl">{errorMessage}</p>
            </div>
        </div>
    );
}