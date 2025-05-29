using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Mapping
{
    public class ShelterMapping : IEntityTypeConfiguration<Shelter>
    {
        public void Configure(EntityTypeBuilder<Shelter> builder)
        {
            builder.ToTable("Shelters");

            builder.HasKey(s => s.Id);

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
