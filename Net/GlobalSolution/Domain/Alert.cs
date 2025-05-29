namespace Projeto.Domain
{
    public class Alert
    {
        public Guid IdAlert { get; private set; }
        public string Title { get; private set; }
        public string Description { get; private set; }
        public DateTime Date { get; private set; }
        public string Type { get; private set; }
        public string Status { get; private set; }

        // EF Core navigation
        public ICollection<DangerArea> DangerAreas { get; private set; }

        // Construtor vazio exigido pelo EF
        protected Alert()
        {
            DangerAreas = new List<DangerArea>();
        }

        public Alert(string title, string description, DateTime date, string type, string status)
        {
            IdAlert = Guid.NewGuid();
            SetTitle(title);
            SetDescription(description);
            SetDate(date);
            SetType(type);
            SetStatus(status);
            DangerAreas = new List<DangerArea>();
        }

        public void SetTitle(string title)
        {
            if (string.IsNullOrWhiteSpace(title))
                throw new ArgumentException("O título não pode ser vazio.");
            Title = title;
        }

        public void SetDescription(string description)
        {
            if (string.IsNullOrWhiteSpace(description))
                throw new ArgumentException("A descrição não pode ser vazia.");
            Description = description;
        }

        public void SetDate(DateTime date)
        {
            if (date < new DateTime(2000, 1, 1))
                throw new ArgumentException("Data inválida.");
            Date = date;
        }

        public void SetType(string type)
        {
            if (string.IsNullOrWhiteSpace(type))
                throw new ArgumentException("O tipo não pode ser vazio.");
            Type = type;
        }

        public void SetStatus(string status)
        {
            if (string.IsNullOrWhiteSpace(status))
                throw new ArgumentException("O status não pode ser vazio.");
            Status = status;
        }
    }
}
