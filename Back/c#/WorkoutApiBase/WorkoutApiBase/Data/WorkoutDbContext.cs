using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

    public class WorkoutDbContext : DbContext
    {
        public WorkoutDbContext (DbContextOptions<WorkoutDbContext> options)
            : base(options)
        {
        }

        public DbSet<Workout> Workout { get; set; } = default!;
    }
