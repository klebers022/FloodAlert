using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class DangerAreaMapping : IEntityTypeConfiguration<DangerArea>
    {
        public void Configure(EntityTypeBuilder<DangerArea> builder)
        {
            // Chave primária com identidade (SQL Server)
            builder.HasKey(d => d.Id);
            builder.Property(d => d.Id)
                   .ValueGeneratedOnAdd();

            // Propriedades
            builder.Property(d => d.Name)
                   .IsRequired()
                   .HasMaxLength(100);

            builder.Property(d => d.Description)
                   .IsRequired()
                   .HasMaxLength(500);

            builder.Property(d => d.ThreatLevel)
                   .IsRequired()
                   .HasMaxLength(50);

            builder.Property(d => d.Coordinates)
                   .IsRequired()
                   .HasMaxLength(200);

            builder.Property(d => d.IdAlert)
                   .IsRequired();

            // Relacionamento
            builder.HasOne(d => d.Alert)
                   .WithMany(a => a.DangerAreas)
                   .HasForeignKey(d => d.IdAlert)
                   .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
