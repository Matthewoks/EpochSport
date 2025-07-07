using Microsoft.EntityFrameworkCore;

namespace WorkoutApiBase
{
    public class MigrationService
    {
        public static void InitializeMigrate(IApplicationBuilder app)
        {
            using var servicescope = app.ApplicationServices.CreateScope();
            var db =servicescope.ServiceProvider.GetService<WorkoutDbContext>();
            db.Database.Migrate();
        }
    }
}
