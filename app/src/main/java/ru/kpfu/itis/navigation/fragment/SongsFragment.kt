package ru.kpfu.itis.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_songs.*
import ru.kpfu.itis.navigation.App
import ru.kpfu.itis.navigation.R
import ru.kpfu.itis.navigation.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class SongsFragment : Fragment() {

    private val navigator = object : Navigator{

        override fun applyCommands(commands: Array<out Command>) {
            commands.forEach {
                when (it) {
                    is Forward -> onForward(it.screen)
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.NewSongsScreen -> {
                    findNavController().navigate(R.id.action_songsFragment_to_newSongsFragment)
                }
                is Screens.PopularSongsScreen -> {
                    findNavController().navigate(R.id.action_songsFragment_to_popularSongsFragment)
                }
                is Screens.FavouritesScreen -> {
                    findNavController().navigate(R.id.action_songsFragment_to_favouritesFragment)
                }
                is Screens.RecentlyListenedSongsScreen -> {
                    findNavController().navigate(R.id.action_songsFragment_to_recentlyListenedFragment)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val router = App.INSTANCE.router

        favourites_btn.setOnClickListener {
            router.navigateTo(Screens.FavouritesScreen)
        }
        new_btn.setOnClickListener {
            router.navigateTo(Screens.NewSongsScreen)
        }
        popular_btn.setOnClickListener {
            router.navigateTo(Screens.PopularSongsScreen)
        }
        recently_listened_btn.setOnClickListener {
            router.navigateTo(Screens.RecentlyListenedSongsScreen)
        }
    }

    override fun onResume() {
        super.onResume()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }
}