using Microsoft.AspNetCore.Mvc;
using System.Text.Json;
using WorkoutApiBase.Services;

namespace WorkoutApiBase.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class WorkoutsController : ControllerBase
    {
        private readonly WorkoutService _service;

        public WorkoutsController(WorkoutService service)
        {
            _service = service;
        }
        [HttpGet]

        //volendo posso passare dal servizio e applicare li la logica
        //public IActionResult GetWorkouts([FromQuery] DateTime month)
        //{
        //    var workouts = _service.GetWorkouts(month);
        //    return Ok(workouts);
        //}
        public IActionResult GetWorkouts([FromQuery] string? month)
        {

            var filePath = Path.Combine(Directory.GetCurrentDirectory(), "Data", "workouts.json");
            if (!System.IO.File.Exists(filePath))
                return NotFound("File JSON non trovato.");
            var json = System.IO.File.ReadAllText(filePath);
            var data = JsonSerializer.Deserialize<List<WorkoutDay>>(json,
                new JsonSerializerOptions { PropertyNameCaseInsensitive = true });

            //if (!string.IsNullOrEmpty(month))
            //{
            //    data = data?.Where(w => w.Date.StartsWith(month)).ToList();
            //}

            return Ok(data);


        }
    }
}


