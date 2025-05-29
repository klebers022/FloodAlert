using System;
using System.ComponentModel.DataAnnotations;

public class AlertRequest
{
    [Required]
    [MaxLength(100)]
    public string Title { get; set; }

    [Required]
    [MaxLength(500)]
    public string Description { get; set; }

    [Required]
    public DateTime Date { get; set; }

    [Required]
    [MaxLength(50)]
    public string Type { get; set; }

    [Required]
    [MaxLength(50)]
    public string Status { get; set; }
}
