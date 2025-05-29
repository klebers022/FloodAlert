using System.ComponentModel.DataAnnotations;

namespace GlobalSolution.DTO.Request
{
    public class DangerAreaRequest
    {
        [Required]
        [MaxLength(100)]
        public string Name { get; set; }

        [Required]
        [MaxLength(500)]
        public string Description { get; set; }

        [Required]
        [MaxLength(50)]
        public string ThreatLevel { get; set; }

        [Required]
        [MaxLength(200)]
        public string Coordinates { get; set; }

        [Required(ErrorMessage = "O campo IdAlert é obrigatório.")]
        public Guid IdAlert { get; set; }
    }
}
