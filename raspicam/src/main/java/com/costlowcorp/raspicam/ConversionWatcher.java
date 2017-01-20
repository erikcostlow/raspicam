/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.costlowcorp.raspicam;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author erik
 */
public class ConversionWatcher {

    public static void main(String[] args) throws IOException {
        final String dir = args.length == 0 ? "." : args[0];
        final Path path = Paths.get(dir);
        final ConversionWatcher watcher = new ConversionWatcher(path);
        watcher.watch();
    }

    private final Path dir;

    private ConversionWatcher(Path dir) {
        this.dir = dir;
    }

    void watch() throws IOException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
        System.out.println("Watchng " + dir);
        for (;;) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    System.out.println("overflow, ignoring.");
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();

                Path child = dir.resolve(filename);
                final String currFile = child.getFileName().toString().toLowerCase();
                if (currFile.endsWith(".ppm")) {
                    convert(child);
                } else {
                    System.out.println("Ignoring " + currFile);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void convert(Path child) {
        final String name = child.getFileName().toString();
        final Path newFile = child.resolveSibling(name.substring(0, name.lastIndexOf('.')) + ".jpg");
        try (InputStream in = Files.newInputStream(child);
                OutputStream out = Files.newOutputStream(newFile);) {
            final BufferedImage img = ImageIO.read(in);
            System.out.println("Converted at " + newFile);
            ImageIO.write(img, "JPG", out);
        } catch (IOException ex) {
            Logger.getLogger(ConversionWatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
