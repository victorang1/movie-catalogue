package com.example.moviecatalogue.repository

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.TvShow

class TvShowRepository {

    fun getTvShowData(): ArrayList<TvShow> {
        val tvShows = arrayListOf<TvShow>()
        tvShows.add(
            TvShow(
                R.drawable.poster_arrow,
                "Arrow",
                "2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "42m",
                65,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_doom_patrol,
                "Doom Patrol",
                "2019",
                "Sci-Fi & Fantasy, Action & Adventure",
                "49m",
                75,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_dragon_ball,
                "Dragon Ball",
                "1986",
                "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                "25m",
                80,
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_fairytail,
                "Fairy Tail",
                "2009",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                "25m",
                75,
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_family_guy,
                "Family Guy",
                "1999",
                "Animation, Comedy",
                "22m",
                68,
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_flash,
                "The Flash",
                "1990",
                "Action & Adventure, Crime, Drama, Sci-Fi & Fantasy",
                "45m",
                72,
                "When a bolt of lightening crashes through a police crime lab, a mix of electrically charged substances bathes chemist Barry Allen, transforming him into the fastest man alive--The Flash."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_god,
                "God Friended Me",
                "2018",
                "Drama, Family, Mystery",
                "42m",
                81,
                "A self-proclaimed \"pesky atheist\" is encouraged to help strangers by someone claiming to be God who friends him on Facebook."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_gotham,
                "Gotham",
                "2014",
                "Drama, Fantasy, Crime",
                "43m",
                74,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?"
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_grey_anatomy,
                "Grey's Anatomy",
                "2005",
                "Drama",
                "43m",
                80,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )
        tvShows.add(
            TvShow(
                R.drawable.poster_hanna,
                "Hanna",
                "2019",
                "Action & Adventure, Drama",
                "50m",
                74,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
            )
        )
        return tvShows
    }
}