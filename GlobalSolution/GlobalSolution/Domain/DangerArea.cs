namespace Projeto.Domain
{
    public class DangerArea
    {
        public Guid Id { get; private set; }
        public string Name { get; private set; }
        public string Description { get; private set; }
        public string ThreatLevel { get; private set; }
        public string Coordinates { get; private set; }

        // Foreign Key
        public Guid IdAlert { get; private set; }

        // Navigation property — necessário para EF Core
        public Alert Alert { get; private set; }

        // EF Core exige construtor vazio
        protected DangerArea() { }

        public DangerArea(string name, string description, string threatLevel, string coordinates, Guid idAlert)
        {
            Id = Guid.NewGuid();
            SetName(name);
            SetDescription(description);
            SetThreatLevel(threatLevel);
            SetCoordinates(coordinates);
            SetAlertId(idAlert);
        }

        public void SetName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("O nome da área não pode ser vazio.");
            Name = name;
        }

        public void SetDescription(string description)
        {
            if (string.IsNullOrWhiteSpace(description))
                throw new ArgumentException("A descrição não pode ser vazia.");
            Description = description;
        }

        public void SetThreatLevel(string level)
        {
            if (string.IsNullOrWhiteSpace(level))
                throw new ArgumentException("O nível de ameaça não pode ser vazio.");
            ThreatLevel = level;
        }

        public void SetCoordinates(string coordinates)
        {
            if (string.IsNullOrWhiteSpace(coordinates))
                throw new ArgumentException("As coordenadas não podem ser vazias.");
            Coordinates = coordinates;
        }

        public void SetAlertId(Guid idAlert)
        {
            if (idAlert == Guid.Empty)
                throw new ArgumentException("Id do alerta inválido.");
            IdAlert = idAlert;
        }
    }
}
