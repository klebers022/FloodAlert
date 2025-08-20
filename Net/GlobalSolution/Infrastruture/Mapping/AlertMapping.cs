using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class AlertMapping : IEntityTypeConfiguration<Alert>
    {
        public void Configure(EntityTypeBuilder<Alert> builder)
        {
            // Chave primária com identidade (SQL Server)
            builder.HasKey(a => a.IdAlert);
            builder.Property(a => a.IdAlert)
                   .ValueGeneratedOnAdd();

            // Propriedades
            builder.Property(a => a.Title)
                   .IsRequired()
                   .HasMaxLength(100);

            builder.Property(a => a.Description)
                   .IsRequired()
                   .HasMaxLength(500);

            builder.Property(a => a.Date)
                   .IsRequired(); // atenção: "Date" é reservada no SQL Server, mas funciona se não houver conflito

            builder.Property(a => a.Type)
                   .IsRequired()
                   .HasMaxLength(50);

            builder.Property(a => a.Status)
                   .IsRequired()
                   .HasMaxLength(50);

            // Relacionamento
            builder.HasMany(a => a.DangerAreas)
                   .WithOne() // se DangerArea não tiver propriedade de navegação
                   .HasForeignKey(d => d.IdAlert)
                   .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
