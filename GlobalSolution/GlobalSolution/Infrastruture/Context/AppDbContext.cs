using Microsoft.EntityFrameworkCore;
using Projeto.Domain;

namespace GlobalSolution.Infrastructure.Context
{
    public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
    {
        public DbSet<Alert> Alerts { get; set; }
        public DbSet<DangerArea> DangerAreas { get; set; }
        public DbSet<Shelter> Shelters { get; set; }
        public DbSet<Incident> Incidents { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfigurationsFromAssembly(typeof(AppDbContext).Assembly);

            // Se quiser manter as chaves explícitas, ok:
            modelBuilder.Entity<Alert>().HasKey(a => a.IdAlert);
            modelBuilder.Entity<DangerArea>().HasKey(d => d.Id);
            modelBuilder.Entity<Shelter>().HasKey(s => s.Id);
            modelBuilder.Entity<Incident>().HasKey(i => i.Id);

            // ✅ Relacionamento agora com propriedade de navegação
            modelBuilder.Entity<DangerArea>()
                .HasOne(d => d.Alert)                // propriedade de navegação
                .WithMany(a => a.DangerAreas)        // coleção de DangerAreas em Alert
                .HasForeignKey(d => d.IdAlert)       // chave estrangeira
                .OnDelete(DeleteBehavior.Cascade);   // cascata opcional

            base.OnModelCreating(modelBuilder);
        }
    }
}
