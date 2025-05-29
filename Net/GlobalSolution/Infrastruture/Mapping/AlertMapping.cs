using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class AlertMapping : IEntityTypeConfiguration<Alert>
    {
        public void Configure(EntityTypeBuilder<Alert> builder)
        {
            builder.ToTable("Alerts");

            builder.HasKey(a => a.IdAlert);

            builder.Property(a => a.Title)
                .IsRequired()
                .HasMaxLength(100);

            builder.Property(a => a.Description)
                .IsRequired()
                .HasMaxLength(500);

            builder.Property(a => a.Date)
                .IsRequired();

            builder.Property(a => a.Type)
                .IsRequired()
                .HasMaxLength(50);

            builder.Property(a => a.Status)
                .IsRequired()
                .HasMaxLength(50);

            // Corrigido: d.Alert removido
            builder.HasMany(a => a.DangerAreas)
                   .WithOne() // sem propriedade de navegação em DangerArea
                   .HasForeignKey(d => d.IdAlert)
                   .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
