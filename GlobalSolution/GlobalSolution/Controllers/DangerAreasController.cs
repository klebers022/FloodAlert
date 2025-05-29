using GlobalSolution.DTO.Request;
using GlobalSolution.Infrastructure.Context;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Projeto.Domain;

[ApiController]
[Route("api/[controller]")]
public class DangerAreasController : ControllerBase
{
    private readonly AppDbContext _context;

    public DangerAreasController(AppDbContext context)
    {
        _context = context;
    }

    // GET: api/DangerAreas
    [HttpGet]
    public async Task<ActionResult<IEnumerable<DangerArea>>> GetDangerAreas()
    {
        return await _context.DangerAreas.ToListAsync();
    }

    // GET: api/DangerAreas/5
    [HttpGet("{id}")]
    public async Task<ActionResult<DangerArea>> GetDangerArea(Guid id)
    {
        var dangerArea = await _context.DangerAreas.FindAsync(id);
        if (dangerArea == null)
            return NotFound();

        return dangerArea;
    }

    // POST: api/DangerAreas
    [HttpPost]
    public async Task<ActionResult<DangerArea>> PostDangerArea([FromBody] DangerAreaRequest request)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        // Verifica se o alerta existe para evitar violação de FK
        var alertExists = await _context.Alerts.AnyAsync(a => a.IdAlert == request.IdAlert);
        if (!alertExists)
            return BadRequest("O alerta informado não existe.");

        // Mapear para entidade
        var dangerArea = new DangerArea(
            request.Name,
            request.Description,
            request.ThreatLevel,
            request.Coordinates,
            request.IdAlert);

        _context.DangerAreas.Add(dangerArea);
        await _context.SaveChangesAsync();

        return CreatedAtAction(nameof(GetDangerArea), new { id = dangerArea.Id }, dangerArea);
    }

    // PUT: api/DangerAreas/5
    [HttpPut("{id}")]
    public async Task<IActionResult> PutDangerArea(Guid id, [FromBody] DangerAreaRequest request)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        var dangerArea = await _context.DangerAreas.FindAsync(id);
        if (dangerArea == null)
            return NotFound();

        var alertExists = await _context.Alerts.AnyAsync(a => a.IdAlert == request.IdAlert);
        if (!alertExists)
            return BadRequest("O alerta informado não existe.");

        // Atualiza a entidade usando os setters (sua lógica de validação encapsulada)
        dangerArea.SetName(request.Name);
        dangerArea.SetDescription(request.Description);
        dangerArea.SetThreatLevel(request.ThreatLevel);
        dangerArea.SetCoordinates(request.Coordinates);
        dangerArea.SetAlertId(request.IdAlert);

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!_context.DangerAreas.Any(e => e.Id == id))
                return NotFound();
            else
                throw;
        }

        return NoContent();
    }

    // DELETE: api/DangerAreas/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteDangerArea(Guid id)
    {
        var dangerArea = await _context.DangerAreas.FindAsync(id);
        if (dangerArea == null)
            return NotFound();

        _context.DangerAreas.Remove(dangerArea);
        await _context.SaveChangesAsync();

        return NoContent();
    }
}
