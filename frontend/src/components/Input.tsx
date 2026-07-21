type InputProps = {
    id: string,
    value: string,
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    errorMessage: string,
    label: string,
    name: string,
}

export default function Input({ value, onChange, errorMessage, label, id, name }: InputProps) {
    return (
        <div className="flex flex-col items-start">
            <label htmlFor={id}>{label}</label>
            <input id={id} name={name} type="text" required value={value} onChange={onChange} />
            <div className="h-12">
                <p className="text-red-600 text-xl">{errorMessage}</p>
            </div>
        </div>
    );
}