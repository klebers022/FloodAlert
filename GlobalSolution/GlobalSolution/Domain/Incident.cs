namespace Projeto.Domain
{
    public class Incident
    {
        public Guid Id { get; private set; }
        public string Description { get; private set; }
        public string Type { get; private set; }
        public string Coordinates { get; private set; }
        public DateTime Date { get; private set; }
        public string Status { get; private set; }

        public Incident(string description, string type, string coordinates, DateTime date, string status)
        {
            Id = Guid.NewGuid();
            SetDescription(description);
            SetType(type);
            SetCoordinates(coordinates);
            SetDate(date);
            SetStatus(status);
        }

        public void SetDescription(string description)
        {
            if (string.IsNullOrWhiteSpace(description))
                throw new ArgumentException("A descrição não pode ser vazia.");
            Description = description;
        }

        public void SetType(string type)
        {
            if (string.IsNullOrWhiteSpace(type))
                throw new ArgumentException("O tipo não pode ser vazio.");
            Type = type;
        }

        public void SetCoordinates(string coordinates)
        {
            if (string.IsNullOrWhiteSpace(coordinates))
                throw new ArgumentException("As coordenadas não podem ser vazias.");
            Coordinates = coordinates;
        }

        public void SetDate(DateTime date)
        {
            if (date < new DateTime(2000, 1, 1))
                throw new ArgumentException("Data inválida.");
            Date = date;
        }

        public void SetStatus(string status)
        {
            if (string.IsNullOrWhiteSpace(status))
                throw new ArgumentException("O status não pode ser vazio.");
            Status = status;
        }
    }
}
