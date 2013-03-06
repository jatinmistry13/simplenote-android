package com.automattic.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import com.simperium.client.Bucket;
import com.automattic.simplenote.models.Note;

/**
 * An activity representing a single Note detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link NoteListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link NoteEditorFragment}.
 */
public class NoteEditorActivity extends SherlockFragmentActivity {
	
	private Bucket<Note> mNotesBucket;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_editor);

		Simplenote application = (Simplenote) getApplication();
		mNotesBucket = application.getNotesBucket();

		// Show the Up button in the action bar.
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(NoteEditorFragment.ARG_ITEM_ID, getIntent().getStringExtra(NoteEditorFragment.ARG_ITEM_ID));
			NoteEditorFragment fragment = new NoteEditorFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().add(R.id.note_detail_container, fragment).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean twoPane = findViewById(R.id.note_detail_container) != null;

		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getSupportMenuInflater();
		if (!twoPane)
			inflater.inflate(R.menu.note_editor, menu);

		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this, NoteListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
