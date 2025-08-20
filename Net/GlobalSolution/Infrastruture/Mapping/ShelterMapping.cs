using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class ShelterMapping : IEntityTypeConfiguration<Shelter>
    {
        public void Configure(EntityTypeBuilder<Shelter> builder)
        {
            // Chave primária com identidade (SQL Server)
            builder.HasKey(s => s.Id);
            builder.Property(s => s.Id)
                   .ValueGeneratedOnAdd();

            // Propriedades
            builder.Property(s => s.Name)
                   .IsRequired()
                   .HasMaxLength(100);

            builder.Property(s => s.TotalCapacity)
                   .IsRequired();

            builder.Property(s => s.NumberOccupied)
                   .IsRequired();

            builder.Property(s => s.Location)
                   .IsRequired()
                   .HasMaxLength(200);

            builder.Property(s => s.Status)
                   .IsRequired();
        }
    }
}
