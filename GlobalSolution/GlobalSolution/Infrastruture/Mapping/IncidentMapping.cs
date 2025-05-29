
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class IncidentMapping : IEntityTypeConfiguration<Incident>
    {
        public void Configure(EntityTypeBuilder<Incident> builder)
        {
            builder.ToTable("Incidents");

            builder.HasKey(i => i.Id);

            builder.Property(i => i.Description)
                .IsRequired()
                .HasMaxLength(500);

            builder.Property(i => i.Type)
                .IsRequired()
                .HasMaxLength(50);

            builder.Property(i => i.Coordinates)
                .IsRequired()
                .HasMaxLength(200);

            builder.Property(i => i.Date)
                .IsRequired();

            builder.Property(i => i.Status)
                .IsRequired()
                .HasMaxLength(50);
        }
    }
}
