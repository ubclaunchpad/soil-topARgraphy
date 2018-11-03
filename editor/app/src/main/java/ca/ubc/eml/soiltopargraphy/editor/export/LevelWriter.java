package ca.ubc.eml.soiltopargraphy.editor.export;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.R;
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

/**
 * Utility class to save a level as JSON.
 *
 * @example
 * LevelWriter writer = new LevelWriter(this, "level", data);
 * writer.write(this);
 * startActivity(writer.share(this));
 * ...
 * writer.delete();
 */
public class LevelWriter {
    private static final String AUTHORITY = "ca.ubc.eml.soiltopargraphy.editor.fileprovider";
    private static final Gson gson = new Gson();

    private File file;
    private List<Flag> data;

    /**
     * @param filename Name of the file to write to internal storage.
     * @param data Data to save as JSON.
     */
    public LevelWriter(final Context context, String filename, List<Flag> data) {
        file = new File(context.getFilesDir(), filename + ".json");
        this.data = data;
    }

    /**
     * Write the data object into a JSON file.
     */
    public void write(final Context context) throws IOException {
        try (FileOutputStream outputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE)) {
            Writer outputStreamWriter = new OutputStreamWriter(outputStream);
            JsonWriter writer = new JsonWriter(outputStreamWriter);
            writer.setIndent("  ");
            writeLevel(writer);
            writer.close();
        }
    }

    /**
     * Creates an Intent to share the exported level.
     * Activate it by calling `startActivity`.
     */
    public Intent share(final Context context) {
        Uri fileUri = FileProvider.getUriForFile(context, AUTHORITY, file);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setDataAndType(fileUri, "application/json");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

        String title = context.getResources().getString(R.string.share_level_title);
        return Intent.createChooser(shareIntent, title);
    }

    /**
     * Delete the JSON file written by this class
     * @return True if successfully deleted
     */
    public boolean delete() {
        return file.delete();
    }

    private void writeLevel(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("flags").beginArray();
        for (Flag flag : data) {
            gson.toJson(flag, Flag.class, writer);
        }
        writer.endArray();
        writer.endObject();
    }
}
