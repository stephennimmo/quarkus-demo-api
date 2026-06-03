package com.stephennimmo.quarkusdemoapi.api;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/stress")
public class StressResource {

    private static final int MAX_SECONDS = 300;

    @GET
    @Path("/{seconds}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stress(@PathParam("seconds") int seconds, @QueryParam("threads") @DefaultValue("1") int threads) {
        if (seconds < 1 || seconds > MAX_SECONDS) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"seconds must be between 1 and " + MAX_SECONDS + "\"}")
                    .build();
        }
        if (threads < 1 || threads > Runtime.getRuntime().availableProcessors()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"threads must be between 1 and " + Runtime.getRuntime().availableProcessors() + "\"}")
                    .build();
        }

        long startTime = System.nanoTime();
        Thread[] workers = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            workers[i] = Thread.ofPlatform().start(() -> burnCpu(seconds));
        }
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("{\"error\":\"stress test interrupted\"}")
                        .build();
            }
        }
        long elapsedMs = (System.nanoTime() - startTime) / 1_000_000;

        return Response.ok("{\"seconds\":" + seconds + ",\"threads\":" + threads + ",\"elapsedMs\":" + elapsedMs + "}").build();
    }

    private void burnCpu(int seconds) {
        long endTime = System.nanoTime() + (seconds * 1_000_000_000L);
        double result = 0;
        while (System.nanoTime() < endTime) {
            result += Math.sqrt(Math.random());
        }
        if (result < 0) {
            throw new IllegalStateException();
        }
    }

}
