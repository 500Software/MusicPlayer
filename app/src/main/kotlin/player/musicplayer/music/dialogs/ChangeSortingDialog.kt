package player.musicplayer.music.dialogs

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.musicplayer.commons.extensions.setupDialogStuff
import com.musicplayer.commons.helpers.SORT_DESCENDING
import com.musicplayer.commons.models.RadioItem
import com.musicplayer.commons.views.MyCompatRadioButton
import player.musicplayer.music.R
import player.musicplayer.music.extensions.config
import player.musicplayer.music.helpers.*
import kotlinx.android.synthetic.main.dialog_change_sorting.view.*

class ChangeSortingDialog(val activity: Activity, val location: Int, val callback: () -> Unit) {
    private val config = activity.config
    private var currSorting = 0
    var view: View = activity.layoutInflater.inflate(R.layout.dialog_change_sorting, null)

    init {
        currSorting = when (location) {
            TAB_PLAYLISTS -> config.playlistSorting
            TAB_ARTISTS -> config.artistSorting
            TAB_ALBUMS -> config.albumSorting
            ACTIVITY_PLAYLIST -> config.playlistTracksSorting
            else -> config.trackSorting
        }

        setupSortRadio()
        setupOrderRadio()

        AlertDialog.Builder(activity)
            .setPositiveButton(R.string.ok) { dialog, which -> dialogConfirmed() }
            .setNegativeButton(R.string.cancel, null)
            .create().apply {
                activity.setupDialogStuff(view, this, R.string.sort_by)
            }
    }

    private fun setupSortRadio() {
        val radioItems = ArrayList<RadioItem>()
        when (location) {
            TAB_PLAYLISTS -> {
                radioItems.add(RadioItem(0, activity.getString(R.string.title), PLAYER_SORT_BY_TITLE))
                radioItems.add(RadioItem(1, activity.getString(R.string.track_count), PLAYER_SORT_BY_TRACK_COUNT))
            }
            TAB_ARTISTS -> {
                radioItems.add(RadioItem(0, activity.getString(R.string.title), PLAYER_SORT_BY_TITLE))
                radioItems.add(RadioItem(1, activity.getString(R.string.album_count), PLAYER_SORT_BY_ALBUM_COUNT))
                radioItems.add(RadioItem(2, activity.getString(R.string.track_count), PLAYER_SORT_BY_TRACK_COUNT))
            }
            TAB_ALBUMS -> {
                radioItems.add(RadioItem(0, activity.getString(R.string.title), PLAYER_SORT_BY_TITLE))
                radioItems.add(RadioItem(1, activity.getString(R.string.artist_name), PLAYER_SORT_BY_ARTIST_TITLE))
                radioItems.add(RadioItem(2, activity.getString(R.string.year), PLAYER_SORT_BY_YEAR))
            }
            TAB_TRACKS, ACTIVITY_PLAYLIST -> {
                radioItems.add(RadioItem(0, activity.getString(R.string.title), PLAYER_SORT_BY_TITLE))
                radioItems.add(RadioItem(1, activity.getString(R.string.artist), PLAYER_SORT_BY_ARTIST_TITLE))
                radioItems.add(RadioItem(2, activity.getString(R.string.duration), PLAYER_SORT_BY_DURATION))
            }
        }

        radioItems.forEach { radioItem ->
            activity.layoutInflater.inflate(R.layout.small_radio_button, null).apply {
                findViewById<MyCompatRadioButton>(R.id.small_radio_button).apply {
                    text = radioItem.title
                    isChecked = currSorting and (radioItem.value as Int) != 0
                    id = radioItem.value as Int
                }

                view.sorting_dialog_radio_sorting.addView(this, RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            }
        }
    }

    private fun setupOrderRadio() {
        val orderRadio = view.sorting_dialog_radio_order
        var orderBtn = orderRadio.sorting_dialog_radio_ascending

        if (currSorting and SORT_DESCENDING != 0) {
            orderBtn = orderRadio.sorting_dialog_radio_descending
        }

        orderBtn.isChecked = true
    }

    private fun dialogConfirmed() {
        val sortingRadio = view.sorting_dialog_radio_sorting
        var sorting = sortingRadio.checkedRadioButtonId

        if (view.sorting_dialog_radio_order.checkedRadioButtonId == R.id.sorting_dialog_radio_descending) {
            sorting = sorting or SORT_DESCENDING
        }

        if (currSorting != sorting) {
            when (location) {
                TAB_PLAYLISTS -> config.playlistSorting = sorting
                TAB_ARTISTS -> config.artistSorting = sorting
                TAB_ALBUMS -> config.albumSorting = sorting
                TAB_TRACKS -> config.trackSorting = sorting
                ACTIVITY_PLAYLIST -> config.playlistTracksSorting = sorting
            }

            callback()
        }
    }
}
