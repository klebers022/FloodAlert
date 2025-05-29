namespace Projeto.Domain
{
    public class Shelter
    {
        public Guid Id { get; private set; }
        public string Name { get; private set; }
        public int TotalCapacity { get; private set; }
        public int NumberOccupied { get; private set; }
        public string Location { get; private set; }
        public bool Status { get; private set; }

        public Shelter(string name, int totalCapacity, int numberOccupied, string location, bool status)
        {
            Id = Guid.NewGuid();
            SetName(name);
            SetTotalCapacity(totalCapacity);
            SetNumberOccupied(numberOccupied);
            SetLocation(location);
            SetStatus(status);
        }

        public void SetName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("O nome do abrigo não pode ser vazio.");
            Name = name;
        }

        public void SetTotalCapacity(int capacity)
        {
            if (capacity <= 0)
                throw new ArgumentException("A capacidade total deve ser maior que zero.");
            TotalCapacity = capacity;
        }

        public void SetNumberOccupied(int occupied)
        {
            if (occupied < 0 || occupied > TotalCapacity)
                throw new ArgumentException("Número de ocupantes inválido.");
            NumberOccupied = occupied;
        }

        public void SetLocation(string location)
        {
            if (string.IsNullOrWhiteSpace(location))
                throw new ArgumentException("A localização não pode ser vazia.");
            Location = location;
        }

        public void SetStatus(bool status)
        {
            Status = status;
        }
    }
}
