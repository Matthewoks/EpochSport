using Microsoft.AspNetCore.Mvc;
using System.Text.Json;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.OpenApi.Models;
using WorkoutApiBase.Repositories;
using WorkoutApiBase.Services;


var builder = WebApplication.CreateBuilder(args);

builder.Services.AddSingleton<WorkoutRepository>();
builder.Services.AddScoped<WorkoutService>();

// Controller
builder.Services.AddControllers();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new OpenApiInfo { Title = "Workout API", Version = "v1" });
});

var app = builder.Build();

// Usa Swagger solo in Development (opzionale)
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapControllers();
//app.MapGet("/", () => "Hello World!");

app.Run();

