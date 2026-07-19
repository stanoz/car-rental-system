import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

function App() {

  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <div className="text-3xl font-bold underline text-blue-500">Frontend</div>
    </QueryClientProvider>
  )
}

export default App
