using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GlobalSolution.Infrastructure.Context;
using Projeto.Domain;

namespace GlobalSolution.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AlertsController : ControllerBase
    {
        private readonly AppDbContext _context;

        public AlertsController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/Alerts
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Alert>>> GetAlerts()
        {
            return await _context.Alerts.ToListAsync();
        }

        // GET: api/Alerts/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Alert>> GetAlert(Guid id)
        {
            var alert = await _context.Alerts.FindAsync(id);

            if (alert == null)
            {
                return NotFound();
            }

            return alert;
        }

        // POST: api/Alerts
        [HttpPost]
        public async Task<ActionResult<Alert>> PostAlert([FromBody] AlertRequest request)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var alert = new Alert(
                request.Title,
                request.Description,
                request.Date,
                request.Type,
                request.Status);

            _context.Alerts.Add(alert);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetAlert), new { id = alert.IdAlert }, alert);
        }

        // PUT: api/Alerts/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAlert(Guid id, [FromBody] AlertRequest request)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var alert = await _context.Alerts.FindAsync(id);
            if (alert == null)
                return NotFound();

            // Atualiza usando setters para validação de domínio
            alert.SetTitle(request.Title);
            alert.SetDescription(request.Description);
            alert.SetDate(request.Date);
            alert.SetType(request.Type);
            alert.SetStatus(request.Status);

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AlertExists(id))
                    return NotFound();
                else
                    throw;
            }

            return NoContent();
        }

        // DELETE: api/Alerts/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAlert(Guid id)
        {
            var alert = await _context.Alerts.FindAsync(id);
            if (alert == null)
                return NotFound();

            _context.Alerts.Remove(alert);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AlertExists(Guid id)
        {
            return _context.Alerts.Any(e => e.IdAlert == id);
        }
    }
}
