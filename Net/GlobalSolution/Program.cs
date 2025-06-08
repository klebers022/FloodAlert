using GlobalSolution.Infrastructure.Context;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

builder.WebHost.UseUrls(Environment.GetEnvironmentVariable("ASPNETCORE_URLS") ?? "http://localhost:5149");

// Configuração do Banco Oracle
builder.Services.AddDbContext<AppDbContext>(options =>
{
    options.UseOracle(builder.Configuration.GetConnectionString("Oracle"));
});

// Configuração do CORS
builder.Services.AddCors(options =>
{
    options.AddPolicy("CorsPolicy", policy =>
        policy
            .AllowAnyOrigin() // No desenvolvimento. Na produção, troque por .WithOrigins("https://seuapp.com")
            .AllowAnyMethod()
            .AllowAnyHeader());
});

// Add Controllers
builder.Services.AddControllers();

// Swagger
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(swagger =>
{
    swagger.SwaggerDoc("v1", new OpenApiInfo
    {
        Title = "Global Solution",
        Description = "Eventos Extremos - " + DateTime.Now.Year,
        Contact = new OpenApiContact
        {
            Name = "Nicolas Barutti",
            Email = "nicolasbarutti2003@gmail.com"
        }
    });
});

var app = builder.Build();

// ** Aplica as migrations automaticamente ao iniciar **
using (var scope = app.Services.CreateScope())
{
    var db = scope.ServiceProvider.GetRequiredService<AppDbContext>();
    db.Database.Migrate();
}

// Pipeline
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c =>
    {
        c.SwaggerEndpoint("/swagger/v1/swagger.json", "Global Solution API v1");
    });
}

// Ativa o CORS antes dos controllers
app.UseCors("CorsPolicy");

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();