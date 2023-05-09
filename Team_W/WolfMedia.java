// class WolfMedia

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WolfMedia {
     static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/spurush";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;
    static Scanner scanner;
    private static final String HORIZONTAL_SEP = "-";
    private static final String verticalSep = "|";
    private static final String joinSep = " ";
    private static String[] headers;
    private static final List<String[]> rows = new ArrayList<>();
    private static boolean rightAlign;

    // queries for Account
    private static PreparedStatement insertAccountQuery;
    private static PreparedStatement updateAccountTypeQuery;
    private static PreparedStatement updateAccountPasswordQuery;
    private static PreparedStatement updateAccountRegistrationDateQuery;
    private static PreparedStatement deleteAccountQuery;
    private static PreparedStatement getAllAccountsQuery;

    // queries for User
    private static PreparedStatement insertUserQuery;
    private static PreparedStatement updateUserFirstNameQuery;
    private static PreparedStatement updateUserLastNameQuery;
    private static PreparedStatement updateUserPhoneQuery;
    private static PreparedStatement updateUserEmailQuery;
    private static PreparedStatement updateUserSubscriptionQuery;
    private static PreparedStatement deleteUserQuery;
    private static PreparedStatement selectUserQuery;
    private static PreparedStatement getAllUsersQuery;
    private static PreparedStatement getUserPaymentQuery;

    // queries for Genre
    private static PreparedStatement insertGenreQuery;
    private static PreparedStatement deleteGenreQuery;
    private static PreparedStatement getAllGenresQuery;

    // queries for Artist
    private static PreparedStatement insertArtistQuery;
    private static PreparedStatement updateArtistNameQuery;
    private static PreparedStatement updateArtistStatusQuery;
    private static PreparedStatement updateArtistTypeQuery;
    private static PreparedStatement updateArtistCountryQuery;
    private static PreparedStatement updateArtistPrimaryGenreQuery;
    private static PreparedStatement updateArtistMonthlyListenersQuery;
    private static PreparedStatement updateArtistRecordLabelAccountIDQuery;
    private static PreparedStatement getArtistByIDQuery;
    private static PreparedStatement updateMonthlyListenersFromSubscribersQuery;
    private static PreparedStatement getAllArtistsQuery;

    // queries for Label
    private static PreparedStatement insertRecordLabelQuery;
    private static PreparedStatement updateRecordLabelNameQuery;
    private static PreparedStatement deleteRecordLabelQuery;
    private static PreparedStatement getAllRecordLabelAccountIDsQuery;
    private static PreparedStatement getAllRecordLabelsQuery;

    // queries for Album
    private static PreparedStatement insertAlbumQuery;
    private static PreparedStatement updateAlbumNameQuery;
    private static PreparedStatement updateAlbumReleaseYearQuery;
    private static PreparedStatement updateAlbumEditionQuery;
    private static PreparedStatement deleteAlbumQuery;
    private static PreparedStatement getAlbumByIDQuery;
    private static PreparedStatement getAllAlbumsQuery;

    // queries for Song
    private static PreparedStatement insertSongQuery;
    private static PreparedStatement updateSongDurationQuery;
    private static PreparedStatement updateSongPlayCountQuery;
    private static PreparedStatement getSongPlayCountQuery;
    private static PreparedStatement updateSongReleaseDateQuery;
    private static PreparedStatement updateSongReleaseCountryQuery;
    private static PreparedStatement updateSongLanguageQuery;
    private static PreparedStatement updateSongRoyaltyRateQuery;
    private static PreparedStatement updateSongTrackNumberQuery;
    private static PreparedStatement updateSongMainArtistQuery;
    private static PreparedStatement addSongToAlbumQuery;
    private static PreparedStatement deleteSongQuery;
    private static PreparedStatement getAllSongsQuery;
    private static PreparedStatement getSongsFromArtistQuery;
    private static PreparedStatement getSongsFromAlbumQuery;

    // queries for SongGenre
    private static PreparedStatement insertSongGenreQuery;
    private static PreparedStatement deleteSongGenreQuery;
    private static PreparedStatement getSongGenreQuery;
    private static PreparedStatement getAllSongGenresQuery;

    // queries for Collaborator
    private static PreparedStatement insertCollaboratorQuery;
    private static PreparedStatement getAllCollaboratorsQuery;

    // queries for Releases
    private static PreparedStatement insertReleaseQuery;
    private static PreparedStatement deleteReleaseQuery;
    private static PreparedStatement getAllReleasesQuery;

    // queries for Subscriber
    private static PreparedStatement insertArtistSubscriberQuery;
    private static PreparedStatement deleteArtistSubscriberQuery;
    private static PreparedStatement getAllArtistSubscribersQuery;

    // queries for PaysLabel
    private static PreparedStatement getAllPaysLabelQuery;

    // queries for PaysArtist
    private static PreparedStatement getAllPaysArtistQuery;

    // queries for Podcast
    private static PreparedStatement insertPodcastQuery;
    private static PreparedStatement updatePodcastNameQuery;
    private static PreparedStatement updatePodcastLanguageQuery;
    private static PreparedStatement updatePodcastCountryQuery;
    private static PreparedStatement updatePodcastEpisodeCountQuery;
    private static PreparedStatement updatePodcastRatingQuery;
    private static PreparedStatement updatePodcastTotalSubscribersQuery;
    private static PreparedStatement deletePodcastQuery;
    private static PreparedStatement getAllPodcastsQuery;

    // queries for PodcastHost
    private static PreparedStatement insertPodcastHostQuery;
    private static PreparedStatement updatePodcastHostFirstNameQuery;
    private static PreparedStatement updatePodcastHostLastNameQuery;
    private static PreparedStatement updatePodcastHostPhoneQuery;
    private static PreparedStatement updatePodcastHostEmailQuery;
    private static PreparedStatement updatePodcastHostCityQuery;
    private static PreparedStatement getPodcastHostbyPodcastHostIDQuery;
    private static PreparedStatement getAllPodcastHostsQuery;

    // queries for Host
    private static PreparedStatement insertHostsQuery;
    private static PreparedStatement selectHostsQuery;
    private static PreparedStatement updatePodcastHostAccountIDHostsQuery;
    private static PreparedStatement updatePodcastIDHostsQuery;
    private static PreparedStatement deleteHostsQuery;
    private static PreparedStatement getAllHostsQuery;

    // queries for PodcastEpisode
    private static PreparedStatement insertPodcastEpisodeQuery;
    private static PreparedStatement addPodcastEpisodeToPodcastQuery;
    private static PreparedStatement updatePodcastEpisodeDurationQuery;
    private static PreparedStatement updatePodcastEpisodeReleaseDateQuery;
    private static PreparedStatement updatePodcastEpisodeListeningCountQuery;
    private static PreparedStatement updatePodcastEpisodeAdvertisementCountQuery;
    private static PreparedStatement deletePodcastEpisodeQuery;
    private static PreparedStatement getAllPodcastEpisodesQuery;
    private static PreparedStatement getEpisodesFromPodcastQuery;

    // queries for PodcastGenre
    private static PreparedStatement insertPodcastGenreQuery;
    private static PreparedStatement deletePodcastGenreQuery;
    private static PreparedStatement getPodcastGenreQuery;
    private static PreparedStatement getAllPodcastGenresQuery;

    // queries for PodcastSubscriber
    private static PreparedStatement insertPodcastSubscriberQuery;
    private static PreparedStatement updatePodcastSubscriberRatingQuery;
    private static PreparedStatement deletePodcastSubscriberQuery;
    private static PreparedStatement getAvgRatingPodcastSubscriberQuery;
    private static PreparedStatement getAllPodcastSubscribersQuery;
    private static PreparedStatement getPodcastSubscriberQuery;

    // queries for Sponsor
    private static PreparedStatement insertSponsorQuery;
    private static PreparedStatement deleteSponsorQuery;
    private static PreparedStatement getAllSponsorsQuery;

    // queries for AffiliatedWith
    private static PreparedStatement insertAffiliatedWithQuery;
    private static PreparedStatement updateAffiliatedWithAmountQuery;
    private static PreparedStatement deleteAffiliatedWithQuery;
    private static PreparedStatement getAllAffiliatedWithQuery;

    // queries for PaysHost
    private static PreparedStatement getAllPaysHostQuery;
    private static PreparedStatement totalPaymentsToPodcastHostQuery;

    // queries for SpecialGuest
    private static PreparedStatement insertSplGuestQuery;
    private static PreparedStatement deleteSplGuestQuery;
    private static PreparedStatement getAllSplGuestsQuery;

    // queries for Invites
    private static PreparedStatement insertInvitesQuery;
    private static PreparedStatement deleteInvitesQuery;
    private static PreparedStatement getAllInvitesQuery;

    // queries for PlayCountPerSong
    private static PreparedStatement getMonthlyPlayCountPerSongQuery;
    private static PreparedStatement getMonthlyPlayCountPerAlbumQuery;
    private static PreparedStatement getMonthlyPlayCountPerArtistQuery;
    private static PreparedStatement totalPaymentsToArtistQuery;
    private static PreparedStatement totalPaymentsToLabelQuery;
    private static PreparedStatement reportAllSongsByArtistQuery;
    private static PreparedStatement reportAllSongsByAlbumQuery;

    // queries for Payment
    private static PreparedStatement getPaymentDateOfPaysLabelQuery;
    private static PreparedStatement getPaymentDateOfPaysArtistQuery;
    private static PreparedStatement getPodcastHostPaymentForTheDateQuery;
    private static PreparedStatement makeRoyaltyPaymentToRecordLabelQuery;
    private static PreparedStatement makeMonthlyRoyaltyPaymentToArtistsQuery;
    private static PreparedStatement makeMonthlyPaymentToPodcastHostQuery;
    private static PreparedStatement receivePaymentFromSubscriberQuery;
    private static PreparedStatement getAllUserPaymentQuery;
    private static PreparedStatement getTotalRevenue;


    // generates Prepared Statements for all queries to perform operations.
    public static void generateDDLAndDMLStatements() {
        String query;
        try {

            // Queries for UserPayment
            query = "SELECT * from `UserPayment`;";
            getAllUserPaymentQuery = connection.prepareStatement(query);

            // Queries for Account
            query = "INSERT INTO `Account` (`accountID`, `type`, `password`, `registrationDate`) VALUES (?, ?, ?, ?);";
            insertAccountQuery = connection.prepareStatement(query);

            query = "UPDATE `Account` SET `type` = ? WHERE `accountID`= ?;";
            updateAccountTypeQuery = connection.prepareStatement(query);

            query = "UPDATE `Account` SET `password` = ? WHERE `accountID`= ?;";
            updateAccountPasswordQuery = connection.prepareStatement(query);

            query = "UPDATE `Account` SET `registrationDate` = ? WHERE `accountID`= ?;";
            updateAccountRegistrationDateQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Account` WHERE `accountID` = ?;";
            deleteAccountQuery = connection.prepareStatement(query);

            query = "SELECT * from `Account`;";
            getAllAccountsQuery = connection.prepareStatement(query);

            // Queries for User
            query = "INSERT INTO `User` (`userAccountID`, `firstName`, `lastName`, `phone`, `email`, `statusOfSubscription`) VALUES (?, ?, ?, ?, ?, ?);";
            insertUserQuery = connection.prepareStatement(query);

            query = "UPDATE `User` SET `firstName` = ? WHERE `userAccountID` = ?;";
            updateUserFirstNameQuery = connection.prepareStatement(query);

            query = "UPDATE `User` SET `lastName` = ? WHERE `userAccountID` = ?;";
            updateUserLastNameQuery = connection.prepareStatement(query);

            query = "UPDATE `User` SET `phone` = ? WHERE `userAccountID` = ?;";
            updateUserPhoneQuery = connection.prepareStatement(query);

            query = "UPDATE `User` SET `email` = ? WHERE `userAccountID` = ?;";
            updateUserEmailQuery = connection.prepareStatement(query);

            query = "UPDATE `User` SET `statusOfSubscription` = ? WHERE `userAccountID` = ?;";
            updateUserSubscriptionQuery = connection.prepareStatement(query);

            query = "DELETE FROM `User` WHERE `userAccountID` = ?;";
            deleteUserQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `User` WHERE `userAccountID` = ?;";
            selectUserQuery = connection.prepareStatement(query);

            query = "SELECT * from `User`;";
            getAllUsersQuery = connection.prepareStatement(query);

            // Queries for Genre
            query = "INSERT INTO `Genre` (`genreName`) VALUES (?);";
            insertGenreQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Genre` WHERE `genreName` = ?;";
            deleteGenreQuery = connection.prepareStatement(query);

            query = "SELECT * from `Genre`;";
            getAllGenresQuery = connection.prepareStatement(query);

            // Queries for RecordLabel
            query = "INSERT INTO `RecordLabel` (`recordLabelAccountID`, `labelName`) VALUES (?, ?);";
            insertRecordLabelQuery = connection.prepareStatement(query);

            query = "UPDATE `RecordLabel` SET `labelName` = ? WHERE `recordLabelAccountID` = ?;";
            updateRecordLabelNameQuery = connection.prepareStatement(query);

            query = "DELETE FROM `RecordLabel` WHERE `recordLabelAccountID` = ?;";
            deleteRecordLabelQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `RecordLabel` WHERE `recordLabelAccountID` = ?;";
            getAllRecordLabelAccountIDsQuery = connection.prepareStatement(query);

            query = "SELECT * from `RecordLabel`;";
            getAllRecordLabelsQuery = connection.prepareStatement(query);

            // Queries for Artist
            query = "INSERT INTO `Artist` (`artistAccountID`, `artistName`, `status`, `type`, `country`, `primaryGenre`, `monthlyListeners`, `recordLabelAccountID`)"
                    +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            insertArtistQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `artistName` = ? WHERE `artistAccountID`= ?;";
            updateArtistNameQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `status` = ? WHERE `artistAccountID`= ?;";
            updateArtistStatusQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `type` = ? WHERE `artistAccountID`= ?;";
            updateArtistTypeQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `country` = ? WHERE `artistAccountID`= ?;";
            updateArtistCountryQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `primaryGenre` = ? WHERE `artistAccountID`= ?;";
            updateArtistPrimaryGenreQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `monthlyListeners` = ? WHERE `artistAccountID`= ?;";
            updateArtistMonthlyListenersQuery = connection.prepareStatement(query);

            query = "UPDATE `Artist` SET `recordLabelAccountID` = ? WHERE `artistAccountID`= ?;";
            updateArtistRecordLabelAccountIDQuery = connection.prepareStatement(query);

            query = "SELECT * from `Artist` WHERE `artistAccountID` = ?;";
            getArtistByIDQuery = connection.prepareStatement(query);

            query = "SELECT * from `Artist`;";
            getAllArtistsQuery = connection.prepareStatement(query);

            // Queries for Album
            query = "INSERT INTO `Album` (`albumID`, `albumName`, `releaseYear`, `edition`) " +
                    "VALUES (?, ?, ?, ?);";
            insertAlbumQuery = connection.prepareStatement(query);

            query = "UPDATE `Album` SET `albumName` = ? WHERE `albumID` = ?;";
            updateAlbumNameQuery = connection.prepareStatement(query);

            query = "UPDATE `Album` SET `releaseYear` = ? WHERE `albumID` = ?;";
            updateAlbumReleaseYearQuery = connection.prepareStatement(query);

            query = "UPDATE `Album` SET `edition` = ? WHERE `albumID` = ?;";
            updateAlbumEditionQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Album` WHERE `albumID` = ?;";
            deleteAlbumQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `Album` WHERE `albumID` = ?;";
            getAlbumByIDQuery = connection.prepareStatement(query);

            query = "SELECT * from `Album`;";
            getAllAlbumsQuery = connection.prepareStatement(query);

            // Queries for Releases
            query = "INSERT INTO `Releases` (`artistAccountID`, `albumID`) VALUES (?, ?);";
            insertReleaseQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Releases` WHERE `artistAccountID` = ? AND `albumID` = ?;";
            deleteReleaseQuery = connection.prepareStatement(query);

            query = "SELECT * from `Releases`;";
            getAllReleasesQuery = connection.prepareStatement(query);

            // Queries for Song
            query = "INSERT INTO `Song` (`albumID`, `songTitle`, `duration`, `playCount`, `releaseDate`, `releaseCountry`, `language`, `royaltyRate`, `trackNumber`, `mainArtist`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            insertSongQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `duration` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongDurationQuery = connection.prepareStatement(query);

            query = "SELECT playCount FROM `Song` WHERE `albumID` = ? AND `songTitle` = ?;";
            getSongPlayCountQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `playCount` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongPlayCountQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `releaseDate` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongReleaseDateQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `releaseCountry` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongReleaseCountryQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `language` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongLanguageQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `royaltyRate` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongRoyaltyRateQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `trackNumber` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongTrackNumberQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `mainArtist` = ? WHERE `albumID` = ? AND `songTitle` = ?;";
            updateSongMainArtistQuery = connection.prepareStatement(query);

            query = "UPDATE `Song` SET `albumID` = ? WHERE `albumID` = ? AND `songTitle` = ?";
            addSongToAlbumQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Song` WHERE `albumID` = ? AND `songTitle` = ?;";
            deleteSongQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `Song`;";
            getAllSongsQuery = connection.prepareStatement(query);

            // Queries for SongGenre
            query = "INSERT INTO `SongGenre` (`genreName`, `songTitle`, `albumID`) VALUES (?, ?, ?);";
            insertSongGenreQuery = connection.prepareStatement(query);

            query = "DELETE FROM `SongGenre` WHERE `genreName` = ? AND `songTitle` = ? AND `albumID` = ?;";
            deleteSongGenreQuery = connection.prepareStatement(query);

            query = "SELECT GenreName FROM `SongGenre` WHERE `songTitle` = ? AND `albumID` = ?;";
            getSongGenreQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `SongGenre`;";
            getAllSongGenresQuery = connection.prepareStatement(query);

            // Queries for Collaborators
            query = "INSERT INTO `Collaborators` (`artistAccountID`, `songTitle`, `albumID`) VALUES (?, ?, ?);";
            insertCollaboratorQuery = connection.prepareStatement(query);

            query = "SELECT * from `Collaborators`;";
            getAllCollaboratorsQuery = connection.prepareStatement(query);

            // Queries for ArtistSubscribers
            query = "INSERT INTO `ArtistSubscribers` (`artistAccountID`, `userAccountID`) VALUES (?, ?);";
            insertArtistSubscriberQuery = connection.prepareStatement(query);

            query = "DELETE FROM `ArtistSubscribers` WHERE `artistAccountID` = ? AND `userAccountID` = ?;";
            deleteArtistSubscriberQuery = connection.prepareStatement(query);

            query = "SELECT * from `ArtistSubscribers`;";
            getAllArtistSubscribersQuery = connection.prepareStatement(query);

            // Queries for PaysLabel
            query = "SELECT * from `PaysLabel`;";
            getAllPaysLabelQuery = connection.prepareStatement(query);

            // Queries for PaysArtist

            query = "SELECT * from `PaysArtist`;";
            getAllPaysArtistQuery = connection.prepareStatement(query);

            // queries for Podcast
            query = "INSERT INTO `Podcast` (`podcastID`, `podcastName`, `language`, `country`, `episodeCount`, `rating`, `totalSubscribers`) VALUES (?, ?, ?, ?, ?, ?, ?);";
            insertPodcastQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `podcastName` = ? WHERE `podcastID` = ?;";
            updatePodcastNameQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `language` = ? WHERE `podcastID` = ?;";
            updatePodcastLanguageQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `country` = ? WHERE `podcastID` = ?;";
            updatePodcastCountryQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `episodeCount` = ? WHERE `podcastID` = ?;";
            updatePodcastEpisodeCountQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `rating` = ? WHERE `podcastID` = ?;";
            updatePodcastRatingQuery = connection.prepareStatement(query);

            query = "UPDATE `Podcast` SET `totalSubscribers` = ? WHERE `podcastID` = ?;";
            updatePodcastTotalSubscribersQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Podcast` WHERE `podcastID` = ?;";
            deletePodcastQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `Podcast`;";
            getAllPodcastsQuery = connection.prepareStatement(query);

            // queries for Podcast host
            query = "INSERT INTO `PodcastHost` (`podcastHostAccountID`, `firstName`, `lastName`, `phone`, `email`, `city`) VALUES (?, ?, ?, ?, ?, ?);";
            insertPodcastHostQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastHost` SET `firstName` = ? WHERE `podcastHostAccountID` = ?;";
            updatePodcastHostFirstNameQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastHost` SET `lastName` = ? WHERE `podcastHostAccountID` = ?;";
            updatePodcastHostLastNameQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastHost` SET `phone` = ? WHERE `podcastHostAccountID` = ?;";
            updatePodcastHostPhoneQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastHost` SET `email` = ? WHERE `podcastHostAccountID` = ?;";
            updatePodcastHostEmailQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastHost` SET `city` = ? WHERE `podcastHostAccountID` = ?;";
            updatePodcastHostCityQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `PodcastHost` WHERE `podcastHostAccountID` = ?;";
            getPodcastHostbyPodcastHostIDQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `PodcastHost`;";
            getAllPodcastHostsQuery = connection.prepareStatement(query);

            // queries for Hosts
            query = "INSERT INTO `Hosts` (`podcastHostAccountID`, `podcastID`) VALUES (?, ?);";
            insertHostsQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `Hosts` WHERE `podcastHostAccountID` = ? AND `podcastID` = ?;";
            selectHostsQuery = connection.prepareStatement(query);

            query = "UPDATE `Hosts` SET  `podcastHostAccountID` = ? WHERE `podcastHostAccountID` = ? AND `podcastID` = ?;";
            updatePodcastHostAccountIDHostsQuery = connection.prepareStatement(query);

            query = "UPDATE `Hosts` SET  `podcastID` = ? WHERE `podcastHostAccountID` = ? AND `podcastID` = ?;";
            updatePodcastIDHostsQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Hosts` WHERE `podcastHostAccountID` = ? AND `podcastID` = ?;";
            deleteHostsQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `Hosts`;";
            getAllHostsQuery = connection.prepareStatement(query);

            // queries for Podcast episode
            query = "INSERT INTO `PodcastEpisode` (`episodeTitle`, `podcastID`, `duration`, `releaseDate`, `listeningCount`, `advertisementCount`) VALUES (?, ?, ?, ?, ?, ?);";
            insertPodcastEpisodeQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastEpisode` SET `podcastID` = ? WHERE `podcastID` = ? AND `episodeTitle` = ?";
            addPodcastEpisodeToPodcastQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastEpisode` SET `duration` = ? WHERE `podcastID` = ? AND `episodeTitle` = ?;";
            updatePodcastEpisodeDurationQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastEpisode` SET `releaseDate` = ? WHERE `podcastID` = ? AND `episodeTitle` = ?;";
            updatePodcastEpisodeReleaseDateQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastEpisode` SET `listeningCount` = ? WHERE `podcastID` = ? AND `episodeTitle` = ?;";
            updatePodcastEpisodeListeningCountQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastEpisode` SET `advertisementCount` = ? WHERE `podcastID` = ? AND `episodeTitle` = ?;";
            updatePodcastEpisodeAdvertisementCountQuery = connection.prepareStatement(query);

            query = "DELETE FROM `PodcastEpisode` WHERE `podcastID` = ? AND `episodeTitle` = ?;";
            deletePodcastEpisodeQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `PodcastEpisode`;";
            getAllPodcastEpisodesQuery = connection.prepareStatement(query);

            // queries for Podcast genre
            query = "INSERT INTO `PodcastGenre` (`genreName`, `podcastID`) VALUES (?, ?);";
            insertPodcastGenreQuery = connection.prepareStatement(query);

            query = "DELETE FROM `PodcastGenre` WHERE `genreName` = ? AND `podcastID` = ?;";
            deletePodcastGenreQuery = connection.prepareStatement(query);

            query = "SELECT GenreName FROM `PodcastGenre` WHERE `podcastID` = ?;";
            getPodcastGenreQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `PodcastGenre`;";
            getAllPodcastGenresQuery = connection.prepareStatement(query);

            // Queries for PodcastSubscribers
            query = "INSERT INTO `PodcastSubscribers` (`podcastID`, `userAccountID`, `rating`) VALUES (?, ?, ?);";
            insertPodcastSubscriberQuery = connection.prepareStatement(query);

            query = "UPDATE `PodcastSubscribers` SET `rating` = ? WHERE `userAccountID` = ? AND `podcastID` = ?;";
            updatePodcastSubscriberRatingQuery = connection.prepareStatement(query);

            query = "DELETE FROM `PodcastSubscribers` WHERE `podcastID` = ? AND `userAccountID` = ?;";
            deletePodcastSubscriberQuery = connection.prepareStatement(query);

            query = "SELECT SUM(rating)/COUNT(*) as avgRating FROM `PodcastSubscribers` WHERE `podcastID` = ?;";
            getAvgRatingPodcastSubscriberQuery = connection.prepareStatement(query);

            query = "SELECT * FROM `PodcastSubscribers` WHERE `podcastID` = ? AND `userAccountID` = ?;";
            getPodcastSubscriberQuery = connection.prepareStatement(query);

            query = "SELECT * from `PodcastSubscribers`;";
            getAllPodcastSubscribersQuery = connection.prepareStatement(query);

            // Queries for PaysHost

            query = "SELECT * from `PaysHost`;";
            getAllPaysHostQuery = connection.prepareStatement(query);

            // queries for Sponsor
            query = "INSERT INTO `Sponsor` (`sponsorID`, `sponsorName`) VALUES (?, ?);";
            insertSponsorQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Sponsor` WHERE `sponsorID` = ?;";
            deleteSponsorQuery = connection.prepareStatement(query);

            query = "SELECT * from `Sponsor`;";
            getAllSponsorsQuery = connection.prepareStatement(query);

            // queries for Affiliated with
            query = "INSERT INTO `AffiliatedWith` (`podcastID`, `sponsorID`, `amount`) VALUES (?, ?, ?);";
            insertAffiliatedWithQuery = connection.prepareStatement(query);

            query = "UPDATE `AffiliatedWith` SET `amount` = ? WHERE `podcastID` = ? AND `sponsorID` = ?;";
            updateAffiliatedWithAmountQuery = connection.prepareStatement(query);

            query = "DELETE FROM `AffiliatedWith` WHERE `podcastID` = ? AND `sponsorID` = ?;";
            deleteAffiliatedWithQuery = connection.prepareStatement(query);

            query = "SELECT * from `AffiliatedWith`;";
            getAllAffiliatedWithQuery = connection.prepareStatement(query);

            // queries for Special guest
            query = "INSERT INTO `SpecialGuest` (`guestID`, `guestName`) VALUES (?, ?);";
            insertSplGuestQuery = connection.prepareStatement(query);

            query = "DELETE FROM `SpecialGuest` WHERE `guestID` = ?;";
            deleteSplGuestQuery = connection.prepareStatement(query);

            query = "SELECT * from `SpecialGuest`;";
            getAllSplGuestsQuery = connection.prepareStatement(query);

            // queries for Invites
            query = "INSERT INTO `Invites` (`guestID`, `podcastID`, `episodeTitle`) VALUES (?, ?, ?);";
            insertInvitesQuery = connection.prepareStatement(query);

            query = "DELETE FROM `Invites` WHERE `guestID` = ? AND `podcastID` = ? AND `episodeTitle` = ?;";
            deleteInvitesQuery = connection.prepareStatement(query);

            query = "SELECT * from `Invites`;";
            getAllInvitesQuery = connection.prepareStatement(query);

            // Find songs given artistID
            query = "SELECT s.albumID, s.songTitle " +
                    "FROM Song s NATURAL JOIN Collaborators c " +
                    "WHERE c.artistAccountID = ? " +
                    "UNION SELECT s.albumID, s.songTitle " +
                    "FROM Song s NATURAL JOIN Releases r NATURAL JOIN Album a " +
                    "Where r.artistAccountID = ?;";
            getSongsFromArtistQuery = connection.prepareStatement(query);

            // Find songs given albumID
            query = "SELECT albumID, songTitle " +
                    "FROM Song " +
                    "Where albumID = ?;";
            getSongsFromAlbumQuery = connection.prepareStatement(query);

            // find episodes given podcastID
            query = "SELECT podcastID, episodeTitle " +
                    "FROM PodcastEpisode " +
                    "Where podcastID = ?;";
            getEpisodesFromPodcastQuery = connection.prepareStatement(query);

            // Update Monthly Listeners
            query = "UPDATE Artist a1 SET monthlyListeners=( " +
                    "SELECT count(*) FROM " +
                    "ArtistSubscribers a2 WHERE " +
                    "a1.artistAccountID=a2.artistAccountID) " +
                    "WHERE a1.artistAccountID=?;";
            updateMonthlyListenersFromSubscribersQuery = connection.prepareStatement(query);

            // Reports
            query = "SELECT * FROM " +
                    "(SELECT albumID, songTitle, MONTH(CURDATE()) as Month, YEAR(CURDATE()) as Year, playCount FROM Song "
                    +
                    "UNION " +
                    "SELECT albumID, songTitle, MONTH(paymentDate) as Month, YEAR(paymentDate) as Year, playCount FROM PaysLabel) x "
                    +
                    "WHERE albumID = ? AND songTitle = ? " +
                    "ORDER BY Year, Month";
            getMonthlyPlayCountPerSongQuery = connection.prepareStatement(query);

            query = "SELECT * FROM " +
                    "(SELECT albumID, songTitle, MONTH(CURDATE()) as Month, YEAR(CURDATE()) as Year, SUM(playCount) as playCount FROM Song "
                    +
                    "GROUP BY albumID " +
                    "UNION " +
                    "SELECT albumID, songTitle, MONTH(paymentDate) as Month, YEAR(paymentDate) as Year, SUM(playCount) as playCount FROM PaysLabel "
                    +
                    "GROUP BY albumID) x " +
                    "WHERE albumID = ? " +
                    "ORDER BY Year, Month";
            getMonthlyPlayCountPerAlbumQuery = connection.prepareStatement(query);

            String sql1 = "SELECT c.artistAccountID, s.albumID, s.songTitle, " +
                    "MONTH(CURDATE()) as Month, YEAR(CURDATE()) as Year, " +
                    "playCount " +
                    "FROM Song s NATURAL JOIN Collaborators c " +
                    "UNION SELECT r.artistAccountID, s.albumID, s.songTitle, " +
                    "MONTH(CURDATE()) as Month, YEAR(CURDATE()) as Year, " +
                    "playCount " +
                    "FROM Song s NATURAL JOIN Releases r NATURAL JOIN Album a";

            String sql2 = "SELECT c.artistAccountID, s.albumID, s.songTitle, " +
                    "MONTH(s.paymentDate) as Month, YEAR(s.paymentDate) as Year, " +
                    "playCount " +
                    "FROM PaysLabel s NATURAL JOIN Collaborators c " +
                    "UNION SELECT r.artistAccountID, s.albumID, s.songTitle, " +
                    "MONTH(s.paymentDate) as Month, YEAR(s.paymentDate) as Year, " +
                    "playCount " +
                    "FROM PaysLabel s NATURAL JOIN Releases r NATURAL JOIN Album a";

            query = "SELECT x.artistAccountID, x.Month, x.Year, SUM(x.playCount) AS TotalPlaycount " +
                    "FROM (" + sql1 + " UNION " + sql2 + ") x " +
                    "WHERE x.artistAccountID = ? " +
                    "GROUP BY x.artistAccountID, x.Month, x.Year " +
                    "ORDER BY x.Year, x.Month";

            getMonthlyPlayCountPerArtistQuery = connection.prepareStatement(query);

            query = "SELECT artistAccountID, SUM(amount) as totalPayment "
                    + "FROM PaysArtist "
                    + "WHERE paymentDate >= ? AND paymentDate <= ? AND artistAccountID = ? "
                    + "GROUP BY artistAccountID";
            totalPaymentsToArtistQuery = connection.prepareStatement(query);

            query = "SELECT recordLabelAccountID, SUM(amount) as totalPayment "
                    + "FROM PaysLabel "
                    + "WHERE paymentDate >= ? AND paymentDate <= ? AND recordLabelAccountID = ? "
                    + "GROUP BY recordLabelAccountID";
            totalPaymentsToLabelQuery = connection.prepareStatement(query);


            query = "SELECT podcastHostAccountID, SUM(amount) as totalPayment FROM PaysHost WHERE paymentDate>=? AND paymentDate<=? AND podcastHostAccountID=? GROUP BY podcastHostAccountID;";

            totalPaymentsToPodcastHostQuery= connection.prepareStatement(query);
            query = "SELECT s.albumID, s.songTitle, s.playCount " +
                    "FROM Song s NATURAL JOIN Collaborators c " +
                    "WHERE c.artistAccountID=? " +
                    "UNION SELECT s.albumID, s.songTitle, s.playCount " +
                    "FROM Song s NATURAL JOIN Releases r NATURAL JOIN Album a " +
                    "Where r.artistAccountID=?;";
            reportAllSongsByArtistQuery = connection.prepareStatement(query);

            query = "SELECT * FROM Song WHERE albumID=?";
            reportAllSongsByAlbumQuery = connection.prepareStatement(query);

            query = "SELECT * FROM PaysLabel WHERE paymentDate = ? AND songTitle = ? AND albumID = ?;";
            getPaymentDateOfPaysLabelQuery = connection.prepareStatement(query);

            query = "SELECT * FROM PaysArtist WHERE paymentDate = ? AND songTitle = ? AND albumID = ?;";
            getPaymentDateOfPaysArtistQuery = connection.prepareStatement(query);

            query = "SELECT * FROM PaysHost WHERE podcastHostAccountID = ? AND paymentDate=?;";
            getPodcastHostPaymentForTheDateQuery  = connection.prepareStatement(query);

            query = "INSERT INTO PaysLabel (songTitle, albumID, recordLabelAccountID, paymentDate, playCount, amount) "
                    +
                    "SELECT s.songTitle, s.albumID, r.recordLabelAccountID, ?, s.playCount, (s.playCount * s.royaltyRate) "
                    +
                    "FROM Song s, RecordLabel r, Artist a " +
                    "WHERE s.mainArtist = a.ArtistAccountID " +
                    "AND a.recordLabelAccountID = r.recordLabelAccountID " +
                    "AND s.songTitle = ? " +
                    "AND s.albumID = ? ";
            makeRoyaltyPaymentToRecordLabelQuery = connection.prepareStatement(query);

            sql1 = "SELECT a1.recordLabelAccountID, c.artistAccountID, s.albumID, s.songTitle, s.playCount, (s.royaltyRate * s.playCount) AS amount "
                    +
                    "FROM Song s " +
                    "NATURAL JOIN Collaborators c " +
                    "INNER JOIN Artist a1 ON a1.artistAccountID = s.mainArtist " +
                    "UNION " +
                    "SELECT a2.recordLabelAccountID, r.artistAccountID, s.albumID, s.songTitle, s.playCount, (s.royaltyRate * s.playCount) AS amount "
                    +
                    "FROM Song s " +
                    "NATURAL JOIN Releases r " +
                    "NATURAL JOIN Album a " +
                    "INNER JOIN Artist a2 ON a2.artistAccountID = s.mainArtist";

            query = "INSERT INTO `PaysArtist` (artistAccountID, recordLabelAccountID, albumID, songTitle, paymentDate, amount) "
                    +
                    "SELECT z.artistAccountID, y.recordLabelAccountID, y.albumID, y.songTitle, ?, (0.7 * y.percent * z.amount) "
                    +
                    "FROM (SELECT x.recordLabelAccountID, x.albumID, x.songTitle, (1/COUNT(*)) AS percent " +
                    "FROM (" + sql1 + ") x " +
                    "GROUP BY x.recordLabelAccountID, x.albumID, x.songTitle) y, " +
                    "(" + sql1 + ") z " +
                    "WHERE y.albumID = z.albumID AND y.songTitle = z.songTitle AND y.albumID = ? AND y.songTitle = ?;";

            makeMonthlyRoyaltyPaymentToArtistsQuery = connection.prepareStatement(query);

            query =  "INSERT INTO PaysHost (`podcastHostAccountID`, `podcastID`, `episodeTitle`, `paymentDate`, `amount`)SELECT Hosts.podcastHostAccountID, Hosts.podcastID, PodcastEpisode.episodeTitle, ?, (10+PodcastEpisode.advertisementCount*5) FROM Hosts,PodcastEpisode WHERE Hosts.podcastID=PodcastEpisode.podcastID AND PodcastEpisode.releaseDate LIKE ? AND Hosts.podcastHostAccountID = ?;";
            makeMonthlyPaymentToPodcastHostQuery = connection.prepareStatement(query);

            query = "INSERT INTO UserPayment(userAccountID, paymentDate)" +
            "SELECT userAccountID, ? FROM User WHERE userAccountID = ?;";
            receivePaymentFromSubscriberQuery = connection.prepareStatement(query);

            query ="SELECT * FROM UserPayment WHERE userAccountID = ? AND paymentDate = ?;";
            getUserPaymentQuery = connection.prepareStatement(query);

            query = "SELECT SUM(amount) as payment from `MonthlyRevenue` WHERE date like ?;";
            getTotalRevenue = connection.prepareStatement(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Error Executing Query");
        }
    }

    public static String getCurrentDate() {

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the current date to "YYYY-MM-DD" string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return currentDate.format(formatter);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // ACCOUNT
    // -----------------------------------------------------------------------------------------------------------------------------------------

    public static void showAccountMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAccount Menu");
            System.out.println("1. Delete Account");
            System.out.println("2. Update Account");
            System.out.println("3. List All Account");
            System.out.println("0. Go Back");
            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "2":
                    updateAccount();
                    break;
                case "1":
                    System.out.println("Enter Account ID");
                    String accountID = scanner.nextLine();
                    deleteAccount(accountID);
                    break;
                case "3":
                    displayItems(getAllAccountsQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Insert new Account
    public static int insertAccount(String accountID, String type, String password) {
        try {

                // Set the parameters
                insertAccountQuery.setString(1, accountID);
                insertAccountQuery.setString(2, type);
                insertAccountQuery.setString(3, password);
                insertAccountQuery.setString(4, getCurrentDate());

                // Execute the query
                insertAccountQuery.executeUpdate();
                return 0;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            }
        return -1;
    }

    // Insert delete Account
    public static void deleteAccount(String accountID) {

        try {
            connection.setAutoCommit(false);
            try {
                deleteAccountQuery.setString(1, accountID);
                int rowsAffected = deleteAccountQuery.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Account deleted successfully!");
                } else {
                    System.out.println("Operation Failed. No account matched your input please try again.");
                }
                connection.commit();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Insert update Account
    public static void updateAccount() {
        String newValue;
        String accountID;
        String option;
        scanner = new Scanner(System.in);
        System.out.println("Enter Account ID");
        accountID = scanner.nextLine();
        System.out.println("1. Update account type");
        System.out.println("2. Update password");
        System.out.println("3. Update registration Date");
        option = scanner.nextLine().strip();
        System.out.println("Enter the new value");
        newValue = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updateAccountTypeQuery.setString(1, newValue);
                        updateAccountTypeQuery.setString(2, accountID);
                        updateAccountTypeQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "2":
                        updateAccountPasswordQuery.setString(1, newValue);
                        updateAccountPasswordQuery.setString(2, accountID);
                        updateAccountPasswordQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "3":
                        updateAccountRegistrationDateQuery.setString(1, newValue);
                        updateAccountRegistrationDateQuery.setString(2, accountID);
                        updateAccountRegistrationDateQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // USER
    // -----------------------------------------------------------------------------------------------------------------------------------------
    public static void showUserMenu() throws SQLException {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. Insert New User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Subscribe to Artist");
            System.out.println("5. Unsubscribe to Artist");
            System.out.println("6. Subscribe to Podcast");
            System.out.println("7. Unsubscribe to Podcast");
            System.out.println("8. User Details by ID");
            System.out.println("9. Give rating to podcast");
            System.out.println("10. Show all Users");
            System.out.println("11. Show all Artist Subscribers");
            System.out.println("12. Show all Podcast Subscribers");
            System.out.println("0. Go back to main menu");
            System.out.println("Enter Choice: ");

            String choice = scanner.next().strip();

            switch (choice) {
                case "1":
                    insertUserMenu();
                    break;
                case "2":
                    updateUserMenu();
                    break;
                case "3":
                    deleteUser();
                    break;
                case "4":
                    subscribeUserToArtist();
                    break;
                case "5":
                    unsubscribeUserFromArtist();
                    break;
                case "6":
                    subscribeUserToPodcast();
                    break;
                case "7":
                    unsubscribeUserFromPodcast();
                    break;
                case "8":
                    getUserByID();
                    break;
                case "9":
                    giveRatingToPodcast();
                    break;
                case "10":
                    displayItems(getAllUsersQuery);
                    break;
                case "11":
                    displayItems(getAllArtistSubscribersQuery);
                    break;
                case "12":
                    displayItems(getAllPodcastSubscribersQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Menu for inserting new User
    public static void insertUserMenu() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter UserAccount ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter account password");
        String password = scanner.nextLine();
        System.out.println("Enter first Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter phone");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        String statusOfSubscription = "expired";

        try {
            connection.setAutoCommit(false);
            if (insertAccount(userAccountID, "User", password) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            if (insertUser(userAccountID, firstName, lastName, phone, email, statusOfSubscription) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            System.out.println("User inserted successfully!");
            connection.commit();
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Insert new User
    public static int insertUser(String userAccountID, String firstName, String lastName, int phone, String email,
            String statusOfSubscription) {
        try {
                // Set the parameters
                insertUserQuery.setString(1, userAccountID);
                insertUserQuery.setString(2, firstName);
                insertUserQuery.setString(3, lastName);
                insertUserQuery.setInt(4, phone);
                insertUserQuery.setString(5, email);
                insertUserQuery.setString(6, statusOfSubscription);
                insertUserQuery.executeUpdate();
                return 0;

        } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
        return -1;
    }

    // Menu for updating User
    public static void updateUserMenu() {
        String value;
        String userAccountID;
        String option;
        scanner = new Scanner(System.in);
        System.out.println("Enter user Account ID");
        userAccountID = scanner.nextLine();
        System.out.println("1. Update first Name");
        System.out.println("2. Update Last Name");
        System.out.println("3. Update phone");
        System.out.println("4. Update email");
        System.out.println("5. Update status of Subscription");
        option = scanner.nextLine();
        System.out.println("Enter the new value");
        value = scanner.nextLine();

        updateUser(userAccountID, option, value);
    }

    // Update User
    public static void updateUser(String userAccountID, String option, String value) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updateUserFirstNameQuery.setString(1, value);
                        updateUserFirstNameQuery.setString(2, userAccountID);
                        updateUserFirstNameQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "2":
                        updateUserLastNameQuery.setString(1, value);
                        updateUserLastNameQuery.setString(2, userAccountID);
                        updateUserLastNameQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "3":
                        updateUserPhoneQuery.setInt(1, Integer.parseInt(value));
                        updateUserPhoneQuery.setString(2, userAccountID);
                        updateUserPhoneQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "4":
                        updateUserEmailQuery.setString(1, value);
                        updateUserEmailQuery.setString(2, userAccountID);
                        updateUserEmailQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "5":
                        updateUserSubscriptionQuery.setString(1, value);
                        updateUserSubscriptionQuery.setString(2, userAccountID);
                        updateUserSubscriptionQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete User
    public static void deleteUser() {
        scanner = new Scanner(System.in);
        System.out.println("Enter user Account ID");
        String userAccountID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deleteUserQuery.setString(1, userAccountID);
                deleteUserQuery.executeUpdate();
                System.out.println("Delete Successful");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Getting user information given his ID
    public static void getUserByID() {
        scanner = new Scanner(System.in);
        System.out.println("Enter user Account ID");
        String userAccountID = scanner.nextLine();
        try {
            selectUserQuery.setString(1, userAccountID);
            result = selectUserQuery.executeQuery();
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }
    }

    // Getting user rating
    public static void giveRatingToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter user Account ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter Rating you want to give for this podcast out of 5");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        try {
            try {
                connection.setAutoCommit(false);
                // check if the user is subscribed to the podcast he wants to rate or not
                getPodcastSubscriberQuery.setString(1, podcastID);
                getPodcastSubscriberQuery.setString(2, userAccountID);
                result = getPodcastSubscriberQuery.executeQuery();
                if (!result.next()) {
                    System.out.println("Podcast Subscriber not found for given IDs");
                    return;
                }
                // update PodcastSubscriber table with the rating the user wants to give
                updatePodcastSubscriberRatingQuery.setDouble(1, rating);
                updatePodcastSubscriberRatingQuery.setString(2, userAccountID);
                updatePodcastSubscriberRatingQuery.setString(3, podcastID);
                updatePodcastSubscriberRatingQuery.executeUpdate();

                //Update average rating for the given podcast considering all existing ratings.
                updatePodcastRating(podcastID);
                connection.commit();  //commit if both operations are successful
                System.out.println(" Rating is added and Podcast rating is updated successfully!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();  // rollback if there is any error in any of the operations
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // SONG
    // -----------------------------------------------------------------------------------------------------------------------------------------

    // Song Menu
    public static void showSongMenu() {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSong Menu");
            System.out.println("1. Insert New Song");
            System.out.println("2. Update Song");
            System.out.println("3. Delete Song");
            System.out.println("4. Add Genre to Song");
            System.out.println("5. Delete Genre from Song");
            System.out.println("6. Add Collaborators to Song");
            System.out.println("7. Enter Song Play Count");
            System.out.println("8. Update Song Play Count");
            System.out.println("9. Get Songs from Artist");
            System.out.println("10. Get Songs from Album");
            System.out.println("11. Add Song to Album");
            System.out.println("12. List all Songs");
            System.out.println("13. List all Song Genres");
            System.out.println("0. Go back to main menu");
            System.out.println("Enter Choice: ");

            String choice = scanner.next().strip();

            switch (choice) {
                case "1":
                    insertSongMenu();
                    break;
                case "2":
                    updateSongMenu();
                    break;
                case "3":
                    deleteSong();
                    break;
                case "4":
                    addGenresToSong();
                    break;
                case "5":
                    deleteGenresFromSong();
                    break;
                case "6":
                    addCollaboratorsToSong();
                    break;
                case "7":
                    enterSongPlayCount();
                    break;
                case "8":
                    updateSongPlayCount();
                    break;
                case "9":
                    getSongsFromArtist();
                    break;
                case "10":
                    getSongsFromAlbum();
                    break;
                case "11":
                    addSongToAlbum();
                    break;
                case "12":
                    displayItems(getAllSongsQuery);
                    break;
                case "13":
                    displayItems(getAllSongGenresQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Menu for inserting new song
    public static void insertSongMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter albumID");
        String albumID = scanner.nextLine();
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter duration (in minutes)");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter play count");
        int playCount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter release date in the format YYYY-MM-DD");
        String releaseDate = scanner.nextLine();
        System.out.println("Enter release country");
        String releaseCountry = scanner.nextLine();
        System.out.println("Enter language");
        String language = scanner.nextLine();
        System.out.println("Enter royalty rate in decimal format (e.g. 0.05 for 5%)");
        double royaltyRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter track number");
        int trackNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter main artist");
        String mainArtist = scanner.nextLine();

        // Call the insertSong() function with the provided inputs
        insertSong(albumID, songTitle, duration, playCount, releaseDate, releaseCountry, language, royaltyRate,
                trackNumber, mainArtist);
    }

    // insert new song
    public static void insertSong(String albumID, String songTitle, int duration, int playCount, String releaseDate,
            String releaseCountry, String language, double royaltyRate, int trackNumber, String mainArtist) {
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                insertSongQuery.setString(1, albumID);
                insertSongQuery.setString(2, songTitle);
                insertSongQuery.setInt(3, duration);
                insertSongQuery.setInt(4, playCount);
                insertSongQuery.setString(5, releaseDate);
                insertSongQuery.setString(6, releaseCountry);
                insertSongQuery.setString(7, language);
                insertSongQuery.setDouble(8, royaltyRate);
                insertSongQuery.setInt(9, trackNumber);
                insertSongQuery.setString(10, mainArtist);

                // Execute the query
                insertSongQuery.executeUpdate();
                System.out.println("Insert Successful");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // delete song
    public static void deleteSong() {
        String albumID;
        String songTitle;
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        albumID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deleteSongQuery.setString(1, albumID);
                deleteSongQuery.setString(2, songTitle);
                deleteSongQuery.executeUpdate();
                System.out.println("Delete Successful");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Menu for updating song
    public static void updateSongMenu() {
        String value;
        String albumID;
        String songTitle;
        String option;
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        albumID = scanner.nextLine();
        System.out.println("1. Update song duration");
        System.out.println("2. Update song play count");
        System.out.println("3. Update song release date (Date format YYYY-DD-MM)");
        System.out.println("4. Update song release country");
        System.out.println("5. Update song language");
        System.out.println("6. Update song royalty rate");
        System.out.println("7. Update song track number");
        System.out.println("8. Update song main artist");
        System.out.println("0. Return to Previous Menu");
        System.out.println("Enter Choice: ");

        option = scanner.nextLine();
        System.out.println("Enter the new value");
        value = scanner.nextLine();

        updateSong(albumID, songTitle, option, value);
    }

    // update song
    public static void updateSong(String albumID, String songTitle, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updateSongDurationQuery.setInt(1, Integer.parseInt(newValue));
                        updateSongDurationQuery.setString(2, albumID);
                        updateSongDurationQuery.setString(3, songTitle);
                        updateSongDurationQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "2":
                        updateSongPlayCountQuery.setInt(1, Integer.parseInt(newValue));
                        updateSongPlayCountQuery.setString(2, albumID);
                        updateSongPlayCountQuery.setString(3, songTitle);
                        updateSongPlayCountQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "3":
                        updateSongReleaseDateQuery.setString(1, newValue);
                        updateSongReleaseDateQuery.setString(2, albumID);
                        updateSongReleaseDateQuery.setString(3, songTitle);
                        updateSongReleaseDateQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "4":
                        updateSongReleaseCountryQuery.setString(1, newValue);
                        updateSongReleaseCountryQuery.setString(2, albumID);
                        updateSongReleaseCountryQuery.setString(3, songTitle);
                        updateSongReleaseCountryQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "5":
                        updateSongLanguageQuery.setString(1, newValue);
                        updateSongLanguageQuery.setString(2, albumID);
                        updateSongLanguageQuery.setString(3, songTitle);
                        updateSongLanguageQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "6":
                        updateSongRoyaltyRateQuery.setDouble(1, Double.parseDouble(newValue));
                        updateSongRoyaltyRateQuery.setString(2, albumID);
                        updateSongRoyaltyRateQuery.setString(3, songTitle);
                        updateSongRoyaltyRateQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "7":
                        updateSongTrackNumberQuery.setInt(1, Integer.parseInt(newValue));
                        updateSongTrackNumberQuery.setString(2, albumID);
                        updateSongTrackNumberQuery.setString(3, songTitle);
                        updateSongTrackNumberQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "8":
                        updateSongMainArtistQuery.setString(1, newValue);
                        updateSongMainArtistQuery.setString(2, albumID);
                        updateSongMainArtistQuery.setString(3, songTitle);
                        updateSongMainArtistQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;
                    
                    case "0":
                        return;

                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // add genres to song
    public static void addGenresToSong() {
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        System.out.println("Enter all Genres (ex: Rock, Pop)");
        String genres = scanner.nextLine();
        String[] genreList = genres.split(",");
        try {
            connection.setAutoCommit(false);
            for (String genre : genreList) {
                try {
                    insertSongGenreQuery.setString(1, genre.strip());
                    insertSongGenreQuery.setString(2, songTitle.strip());
                    insertSongGenreQuery.setString(3, albumID.strip());
                    insertSongGenreQuery.executeUpdate();
                    System.out.println("Insert Successful");
                    connection.commit();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    connection.rollback();
                    System.out.println("Operation Failed. Try Again with Valid Inputs");
                } finally {
                    connection.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // displaying all the genres for song
    public static void displayAllSongGenres(String albumID, String songTitle) {
        try {
            getSongGenreQuery.setString(1, songTitle);
            getSongGenreQuery.setString(2, albumID);
            result = getSongGenreQuery.executeQuery();
            if (!result.next()) {
                System.out.println("No Such Song exists");
                return;
            }
            System.out.println("Genres of the Song : ");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }

    }

    // delete genres for song
    public static void deleteGenresFromSong() {
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        displayAllSongGenres(albumID, songTitle);

        System.out.println("Enter all Genres to delete (ex: Rock, Pop)");
        String genres = scanner.nextLine();
        String[] genreList = genres.split(",");
        try {
            connection.setAutoCommit(false);
            try {
                for (String genre : genreList) {
                    deleteSongGenreQuery.setString(1, genre.strip());
                    deleteSongGenreQuery.setString(2, songTitle);
                    deleteSongGenreQuery.setString(3, albumID);
                    deleteSongGenreQuery.executeUpdate();
                    System.out.println("Delete Successful");
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // add collaborators to Song
    public static void addCollaboratorsToSong() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist ID");
        String artistID = scanner.nextLine();
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                insertCollaboratorQuery.setString(1, artistID);
                insertCollaboratorQuery.setString(2, songTitle);
                insertCollaboratorQuery.setString(3, albumID);
                insertCollaboratorQuery.executeUpdate();
                System.out.println("Insert Successful");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // enter new play count for the given song 
    public static void enterSongPlayCount() {
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        System.out.println("Enter new play count value");
        String playCount = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                updateSongPlayCountQuery.setString(1, playCount);
                updateSongPlayCountQuery.setString(2, albumID);
                updateSongPlayCountQuery.setString(3, songTitle);
                updateSongPlayCountQuery.executeUpdate();
                connection.commit();
                System.out.println("Update Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // add song to the album
    public static void addSongToAlbum() {
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        System.out.println("Enter new album ID");
        String newvalue = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                addSongToAlbumQuery.setString(1, newvalue);
                addSongToAlbumQuery.setString(2, albumID);
                addSongToAlbumQuery.setString(3, songTitle);

                int rowsAffected = addSongToAlbumQuery.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Song added to album successfully!");
                } else {
                    System.out.println("Operation Failed. Unable to find specified Song");
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // get the play count from given song
    public static int getPlayCountFromSong(String albumID, String songTitle) {
        try {
            getSongPlayCountQuery.setString(1, albumID);
            getSongPlayCountQuery.setString(2, songTitle);
            result = getSongPlayCountQuery.executeQuery();
            if (!result.next()) {
                return -1;
            }
            return result.getInt(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }
        return -1;
    }

    // update song play count with given value
    public static void updateSongPlayCount() {
        scanner = new Scanner(System.in);
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        songTitle = songTitle.strip();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine().strip();
        System.out.println("Enter new play count value");
        int playCount = Integer.parseInt(scanner.nextLine().strip());

        int currentPlayCount = getPlayCountFromSong(albumID, songTitle);
        if (currentPlayCount == -1) {
            System.out.println("Song not found. Please try again!");
            return;
        }
        try {
            connection.setAutoCommit(false);
            try {
                updateSongPlayCountQuery.setString(1, "" + (currentPlayCount + playCount));
                updateSongPlayCountQuery.setString(2, albumID);
                updateSongPlayCountQuery.setString(3, songTitle);
                updateSongPlayCountQuery.executeUpdate();
                connection.commit();
                System.out.println("Update Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Given an artist get all the songs
    public static void getSongsFromArtist() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artistAccountID");
        String artistAccountID = scanner.nextLine();
        try {
            getSongsFromArtistQuery.setString(1, artistAccountID);
            getSongsFromArtistQuery.setString(2, artistAccountID);
            result = getSongsFromArtistQuery.executeQuery();
            if (!result.next()) {
                System.out.println("No Songs foung for the artist");
                return;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }
    }

    // given an album, get all the songs
    public static void getSongsFromAlbum() {
        scanner = new Scanner(System.in);
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        try {
            getSongsFromAlbumQuery.setString(1, albumID);
            result = getSongsFromAlbumQuery.executeQuery();
            if (!result.next()) {
                System.out.println("No songs found in the album.");
                return;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed.");
        }
    }

    // given a podcast, get all the episodes
    public static void getEpisodesFromPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcastID");
        String podcastID = scanner.nextLine();
        try {
            getEpisodesFromPodcastQuery.setString(1, podcastID);
            result = getEpisodesFromPodcastQuery.executeQuery();
            if (!result.next()) {
                System.out.println("No Episodes found for this Podcast");
                return;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // ARTIST
    // -----------------------------------------------------------------------------------------------------------------------------------------
    // show menu for Artist
    public static void showArtistMenu() throws SQLException {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nArtist Menu");
            System.out.println("1. Insert New Artist");
            System.out.println("2. Update Artist");
            System.out.println("3. Delete Artist");
            System.out.println("4. Add Artist to Album");
            System.out.println("5. Add Artist to Record Label");
            System.out.println("6. Enter Monthly Listeners");
            System.out.println("7. Update Monthly Listeners");
            System.out.println("8. List All Artists");
            System.out.println("9. List All Artist Subscribers");
            System.out.println("10. List All Collaborators");
            System.out.println("11. Remove Artist from Album");
            System.out.println("12. List all releases");
            System.out.println("0. Go back to main menu");
            System.out.println("Enter Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    insertArtistMenu();
                    break;
                case "2":
                    updateArtistMenu();
                    break;
                case "3":
                    deleteArtist();
                    break;
                case "4":
                    addArtistToAlbum();
                    break;
                case "5":
                    addArtistToRecordLabel();
                    break;
                case "6":
                    enterMonthlyListeners();
                    break;
                case "7":
                    updateMonthlyListeners();
                    break;
                case "8":
                    displayItems(getAllArtistsQuery);
                    break;
                case "9":
                    displayItems(getAllArtistSubscribersQuery);
                    break;
                case "10":
                    displayItems(getAllCollaboratorsQuery);
                    break;
                case "11":
                    deleteRelease();
                    break;
                case "12":
                    displayItems(getAllReleasesQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Menu for inserting new Artist
    public static void insertArtistMenu() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter artistAccountID");
        String artistAccountID = scanner.nextLine();
        System.out.println("Enter account password");
        String password = scanner.nextLine();
        System.out.println("Enter artistName");
        String artistName = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        System.out.println("Enter type");
        String type = scanner.nextLine();
        System.out.println("Enter country");
        String country = scanner.nextLine();
        System.out.println("Enter primaryGenre");
        String primaryGenre = scanner.nextLine();
        System.out.println("Enter recordLabelAccountID");
        String recordLabelAccountID = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            // register the new account with global 'Account' table first
            if (insertAccount(artistAccountID, "Artist", password) == -1) {
                connection.rollback();
                return;
            }
            // insert the details into Artist table - this will only succeed if the account details exist in 'Account'
            // because of foreign key constraints
            if (insertArtist(artistAccountID, artistName, status, type, country, primaryGenre, 0, recordLabelAccountID) == -1) {
                connection.rollback();
                return;
            }
            connection.commit();  //commit only if both statements are executed
            System.out.println("Artist inserted successfully!");
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();  // rollback if there are any errors in the execution
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Insert a new Artist
    public static int insertArtist(String artistAccountID, String artistName, String status, String type,
        String country,
        String primaryGenre, int monthlyListeners, String recordLabelAccountID) {
        try {

            // Set the parameters
            insertArtistQuery.setString(1, artistAccountID);
            insertArtistQuery.setString(2, artistName);
            insertArtistQuery.setString(3, (status.isEmpty() ? null : status));
            insertArtistQuery.setString(4, (type.isEmpty() ? null : type));
            insertArtistQuery.setString(5, (country.isEmpty() ? null : country));
            insertArtistQuery.setString(6, (primaryGenre.isEmpty() ? null : primaryGenre));
            insertArtistQuery.setInt(7, monthlyListeners);
            insertArtistQuery.setString(8, recordLabelAccountID);

            // Execute the query
            insertArtistQuery.executeUpdate();
            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
        return -1;
    }

    // get Artist given ID
    public static int getArtistByID(String artistID) {
        try {
            getArtistByIDQuery.setString(1, artistID);
            result = getArtistByIDQuery.executeQuery();

            // Check if result set is empty
            if (!result.next())
                return -1;

            result.beforeFirst();
            display_table(result);
            System.out.println();
            return 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
            return -1;
        }
    }

    // Menu for updating artist
    public static void updateArtistMenu() {

        scanner = new Scanner(System.in);
        System.out.println("Enter artist account ID:");
        String artistAccountID = scanner.nextLine();
        if (getArtistByID(artistAccountID) == -1) {
            System.out.println("Artist not found. Please try again!");
            return;
        }
        System.out.println("1. Update artist name");
        System.out.println("2. Update artist status");
        System.out.println("3. Update artist type");
        System.out.println("4. Update artist country");
        System.out.println("5. Update artist primary genre");
        System.out.println("6. Update artist monthly listeners");
        System.out.println("7. Update artist record label");
        System.out.println("0. Go back to main menu");
        System.out.println("Enter Choice: ");
        String option = scanner.nextLine();
        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        updateArtist(artistAccountID, option, newValue);
    }

    // Updating artist information
    public static void updateArtist(String artistAccountID, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updateArtistNameQuery.setString(1, newValue);
                        updateArtistNameQuery.setString(2, artistAccountID);
                        updateArtistNameQuery.executeUpdate();
                        break;
                    case "2":
                        updateArtistStatusQuery.setString(1, (newValue.isEmpty() ? null : newValue));
                        updateArtistStatusQuery.setString(2, artistAccountID);
                        updateArtistStatusQuery.executeUpdate();
                        break;

                    case "3":
                        updateArtistTypeQuery.setString(1, (newValue.isEmpty() ? null : newValue));
                        updateArtistTypeQuery.setString(2, artistAccountID);
                        updateArtistTypeQuery.executeUpdate();
                        break;

                    case "4":
                        updateArtistCountryQuery.setString(1, (newValue.isEmpty() ? null : newValue));
                        updateArtistCountryQuery.setString(2, artistAccountID);
                        updateArtistCountryQuery.executeUpdate();
                        break;

                    case "5":
                        updateArtistPrimaryGenreQuery.setString(1, newValue);
                        updateArtistPrimaryGenreQuery.setString(2, artistAccountID);
                        updateArtistPrimaryGenreQuery.executeUpdate();
                        break;

                    case "6":
                        updateArtistMonthlyListenersQuery.setInt(1, Integer.parseInt(newValue));
                        updateArtistMonthlyListenersQuery.setString(2, artistAccountID);
                        updateArtistMonthlyListenersQuery.executeUpdate();
                        break;

                    case "7":
                        updateArtistRecordLabelAccountIDQuery.setString(1, newValue);
                        updateArtistRecordLabelAccountIDQuery.setString(2, artistAccountID);
                        updateArtistRecordLabelAccountIDQuery.executeUpdate();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
                System.out.println("Artist details updated successfully!");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete artist
    public static void deleteArtist() {
        String artistID;
        scanner = new Scanner(System.in);
        System.out.println("Enter artist ID:");
        artistID = scanner.nextLine();
        deleteAccount(artistID);
    }

    // add given artist to given album
    public static void addArtistToAlbum() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist ID");
        String artistAccountID = scanner.nextLine();
        System.out.println("Enter album ID");
        String albumID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                insertReleaseQuery.setString(1, artistAccountID);
                insertReleaseQuery.setString(2, albumID);
                insertReleaseQuery.executeUpdate();
                System.out.println("Added artist to album successfully!");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // add given artist to a record label
    public static void addArtistToRecordLabel() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist ID");
        String artistAccountID = scanner.nextLine();
        System.out.println("Enter record label ID");
        String recordLabelAccountID = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                updateArtistRecordLabelAccountIDQuery.setString(1, recordLabelAccountID);
                updateArtistRecordLabelAccountIDQuery.setString(2, artistAccountID);
                updateArtistRecordLabelAccountIDQuery.executeUpdate();
                System.out.println("Added artist to record label successfully!");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Subscribe user to a given artist
    public static void subscribeUserToArtist() {
        scanner = new Scanner(System.in);
        System.out.println("Enter User ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter artist ID");
        String artistAccountID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                insertArtistSubscriberQuery.setString(1, artistAccountID);
                insertArtistSubscriberQuery.setString(2, userAccountID);
                insertArtistSubscriberQuery.executeUpdate();
                System.out.println("User successfully subscribed to the given artist");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // unsubscribe from the given artist
    public static void unsubscribeUserFromArtist() {
        scanner = new Scanner(System.in);
        System.out.println("Enter User ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter artist ID");
        String artistAccountID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deleteArtistSubscriberQuery.setString(1, artistAccountID);
                deleteArtistSubscriberQuery.setString(2, userAccountID);
                deleteArtistSubscriberQuery.executeUpdate();
                System.out.println("User successfully unsubscribed to the given artist");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Enter a new monthly listener value
    public static void enterMonthlyListeners() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist accountID: ");
        String artistAccountID = scanner.nextLine();
        System.out.println("Enter monthly listeners count: ");
        int monthlyListeners = scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            try {
                updateArtistMonthlyListenersQuery.setInt(1, monthlyListeners);
                updateArtistMonthlyListenersQuery.setString(2, artistAccountID);
                int rowsAffected = updateArtistMonthlyListenersQuery.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Monthly listeners count updated successfully!");
                } else {
                    System.out.println("Artist not found or update failed.");
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Update monthly listeners
    public static void updateMonthlyListeners() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist accountID: ");
        String artistAccountID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                updateMonthlyListenersFromSubscribersQuery.setString(1, artistAccountID);
                int rowsAffected = updateMonthlyListenersFromSubscribersQuery.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Monthly listeners count updated successfully!");
                } else {
                    System.out.println("Artist not found or update failed.");
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // RECORD LABEL
    // -----------------------------------------------------------------------------------------------------------------------------------------

    // Menu for Record Label
    public static void showRecordLabelMenu() throws SQLException {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nRecord Label Menu");
            System.out.println("1. Add new record label");
            System.out.println("2. Update record label information");
            System.out.println("3. Delete record label");
            System.out.println("4. List all record labels");
            System.out.println("0. Return to Previous Menu");
            System.out.println("Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertRecordLabelMenu();
                    break;
                case "2":
                    updateRecordLabel();
                    break;
                case "3":
                    deleteRecordLabel();
                    break;
                case "4":
                    displayItems(getAllRecordLabelsQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Menu for inserting new Record Label 
    public static void insertRecordLabelMenu() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter Record Label Account ID");
        String recordLabelAccountID = scanner.nextLine();
        System.out.println("Enter account password");
        String password = scanner.nextLine();
        System.out.println("Enter labelName");
        String labelName = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            if (insertAccount(recordLabelAccountID, "Record Label", password) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            if (insertRecordLabel(recordLabelAccountID, labelName) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            System.out.println("Record Label inserted successfully!");
            connection.commit();
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Insert new Record Label
    public static int insertRecordLabel(String recordLabelAccountID, String labelName) {
        try {
            // Set the parameters
            insertRecordLabelQuery.setString(1, recordLabelAccountID);
            insertRecordLabelQuery.setString(2, labelName);
            // Execute the query
            insertRecordLabelQuery.executeUpdate();
            return 0;

        } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        } 
        return -1;
    }

    // Update record Label
    public static void updateRecordLabel() {
        scanner = new Scanner(System.in);

        System.out.println("Enter Record Label Account ID:");
        String recordLabelAccountID = scanner.nextLine();
        if (getRecordLabelByID(recordLabelAccountID) == -1) {
            System.out.println("Record Label Account not found. Please try again!");
            return;
        }

        System.out.println("Enter the new value for Record Label name:");
        String labelName = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                updateRecordLabelNameQuery.setString(1, labelName);
                updateRecordLabelNameQuery.setString(2, recordLabelAccountID);
                updateRecordLabelNameQuery.executeUpdate();
                // Execute the query
                connection.commit();
                System.out.println(" Updated successfully!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }

    }

    // get the information of a record label given ID
    public static int getRecordLabelByID(String recordLabelAccountID) {
        try {
            getAllRecordLabelAccountIDsQuery.setString(1, recordLabelAccountID);
            result = getAllRecordLabelAccountIDsQuery.executeQuery();
            if (!result.next()) {
                return -1;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
            return -1;
        }
        return 0;
    }

    // Delete a Record Label given ID  
    public static void deleteRecordLabel() {
        String recordLabelAccountID;
        scanner = new Scanner(System.in);
        System.out.println("Enter Record Label Account ID:");
        recordLabelAccountID = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                deleteAccount(recordLabelAccountID);
                deleteRecordLabelQuery.setString(1, recordLabelAccountID);
                deleteRecordLabelQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // ALBUM
    // -----------------------------------------------------------------------------------------------------------------------------------------
    
    // Menu for Album
    public static void showAlbumMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAlbum Menu");
            System.out.println("1. Add new album");
            System.out.println("2. Update album");
            System.out.println("3. Delete album");
            System.out.println("4. List all albums");
            System.out.println("0. Return to Previous Menu");
            System.out.println("Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertAlbumMenu();
                    break;
                case "2":
                    updateAlbumMenu();
                    break;
                case "3":
                    deleteAlbum();
                    break;
                case "4":
                    displayItems(getAllAlbumsQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Menu for inserting new album
    public static void insertAlbumMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter Album ID");
        String albumID = scanner.nextLine();
        System.out.println("Enter albumName");
        String albumName = scanner.nextLine();
        System.out.println("Enter releaseYear");
        String releaseYear = scanner.nextLine();
        System.out.println("Enter edition");
        String edition = scanner.nextLine();

        insertAlbum(albumID, albumName, releaseYear, edition);
    }

    // Insert a new Album
    public static void insertAlbum(String albumID, String albumName, String releaseYear, String edition) {
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                insertAlbumQuery.setString(1, albumID);
                insertAlbumQuery.setString(2, albumName);
                insertAlbumQuery.setString(3, releaseYear);
                insertAlbumQuery.setString(4, edition);

                // Execute the query
                insertAlbumQuery.executeUpdate();
                connection.commit();

                System.out.println("Insert Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Get album given ID
    public static int getAlbumByID(String albumID) {
        try {
            getAlbumByIDQuery.setString(1, albumID);
            result = getAlbumByIDQuery.executeQuery();
            if (!result.next()) {
                return -1;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
            return -1;
        }
        return 0;
    }

    // Menu for updating album
    public static void updateAlbumMenu() {

        scanner = new Scanner(System.in);
        System.out.println("Enter album ID:");
        String albumID = scanner.nextLine();
        if (getAlbumByID(albumID) == -1) {
            System.out.println("Album not found. Please try again!");
            return;
        }
        System.out.println("1. Update album name");
        System.out.println("2. Update album release year");
        System.out.println("3. Update album edition");
        System.out.println("0. Return to previous menu");
        System.out.println("Enter Choice: ");
        String option = scanner.nextLine();
        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        updateAlbum(albumID, option, newValue);
    }

    // Update Album
    public static void updateAlbum(String albumID, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updateAlbumNameQuery.setString(1, newValue);
                        updateAlbumNameQuery.setString(2, albumID);
                        updateAlbumNameQuery.executeUpdate();
                        break;

                    case "2":
                        updateAlbumReleaseYearQuery.setString(1, newValue);
                        updateAlbumReleaseYearQuery.setString(2, albumID);
                        updateAlbumReleaseYearQuery.executeUpdate();
                        break;

                    case "3":
                        updateAlbumEditionQuery.setString(1, newValue);
                        updateAlbumEditionQuery.setString(2, albumID);
                        updateAlbumEditionQuery.executeUpdate();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                System.out.println("Album details updated successfully!");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete album
    public static void deleteAlbum() {
        String albumID;
        scanner = new Scanner(System.in);
        System.out.println("Enter album ID:");
        albumID = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                deleteAlbumQuery.setString(1, albumID);
                deleteAlbumQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete release information
    private static void deleteRelease() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artist Account ID:");
        String artistAccountID = scanner.nextLine();
        System.out.println("Enter album ID:");
        String albumID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deleteReleaseQuery.setString(1, artistAccountID);
                deleteReleaseQuery.setString(2, albumID);
                deleteReleaseQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }

    }

    // -----------------------------------------------------------------------------------------------
    // Payments
    // -----------------------------------------------------------------------------------------------

    // Menu for Payments
    public static void showPaymentsMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPayments Menu");
            System.out.println("1. Make royalty payment to record label for a song");
            System.out.println("2. Make payment to artists for a song");
            System.out.println("3. Make payment to podcast host");
            System.out.println("4. Receive payment from subscriber");
            System.out.println("5. List all artist Payments");
            System.out.println("6. List all label Payments");
            System.out.println("7. List all host Payments");
            System.out.println("8. List all user Payments");
            System.out.println("0.Exit");

            System.out.print("\n Enter Choice: ");

            String choice = scanner.nextLine().strip();

             switch (choice) {
                case "1":
                    makeRoyaltyPaymentToRecordLabel();
                    break;
                case "2":
                    makeMonthlyRoyaltyPaymentToArtists();
                    break;
                case "3":
                    makeMonthlyPaymentToPodcastHost();
                    break;
                case "4":
                    receivePaymentFromSubscriber();
                    break;
                case "5":
                    displayItems(getAllPaysArtistQuery);
                    break;
                case "6":
                    displayItems(getAllPaysLabelQuery);
                    break;
                case "7":
                    displayItems(getAllPaysHostQuery);
                    break;
                case "8":
                    displayItems(getAllUserPaymentQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Calculating payment from the number of subscribers
    public static void receivePaymentFromSubscriber() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the payment year(YYYY): ");
            String paymentYear = scanner.nextLine();
            System.out.println("Enter payment month(MM): ");
            String paymentMonth = scanner.nextLine();
            String paymentDate = paymentYear + "-" + paymentMonth+"-01";
            System.out.println("Enter User ID:");
            String userAccountID = scanner.nextLine();

            if (checkIfUserPaymentExists(paymentDate, userAccountID)) {
                System.out.println("User Payment has been done for the given information.");
                return;
            }
            receivePaymentFromSubscriberQuery.setString(1, paymentDate);
            receivePaymentFromSubscriberQuery.setString(2, userAccountID);
            // Execute the query
            int rowsAffected = receivePaymentFromSubscriberQuery.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected == 1) {
                System.out.println("User Payment has been done successfully");
                updateUserSubscriptionQuery.setString(1,"active");
                updateUserSubscriptionQuery.setString(2, userAccountID);
                updateUserSubscriptionQuery.executeUpdate();
            } else {
                System.out.println("payment was not successful try again");
            }

        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    // checking if the User payment exists
    private static boolean checkIfUserPaymentExists(String paymentDate, String userAccountID) {
        try {
            getUserPaymentQuery.setString(2, paymentDate);
            getUserPaymentQuery.setString(1, userAccountID);

            result = getUserPaymentQuery.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // checking if the Artist payment exists
    public static boolean checkIfArtistPaymentExists(String paymentDate, String songTitle, String albumID) {
        try {
            getPaymentDateOfPaysArtistQuery.setString(1, paymentDate);
            getPaymentDateOfPaysArtistQuery.setString(2, songTitle);
            getPaymentDateOfPaysArtistQuery.setString(3, albumID);

            result = getPaymentDateOfPaysArtistQuery.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                if (count > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // checking if the Label payment exists
    public static boolean checkIfLabelPaymentExists(String paymentDate, String songTitle, String albumID) {
        try {
            getPaymentDateOfPaysLabelQuery.setString(1, paymentDate);
            getPaymentDateOfPaysLabelQuery.setString(2, songTitle);
            getPaymentDateOfPaysLabelQuery.setString(3, albumID);

            result = getPaymentDateOfPaysLabelQuery.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                if (count > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Making Royalty Payment to given Record Label
    public static void makeRoyaltyPaymentToRecordLabel() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the payment year(YYYY): ");
            String paymentYear = scanner.nextLine();
            System.out.println("Enter payment month(MM): ");
            String paymentMonth = scanner.nextLine();
            String paymentDate = paymentYear + "-" + paymentMonth + "-01";
            System.out.println("Enter song title: ");
            String songTitle = scanner.nextLine();
            System.out.println("Enter album ID: ");
            String albumID = scanner.nextLine();

            if (checkIfLabelPaymentExists(paymentDate, songTitle, albumID)) {
                System.out.println(
                        "Royalty payment has been made for the specified song, album ID, and record label account ID.");
                return;
            }
            makeRoyaltyPaymentToRecordLabelQuery.setString(1, paymentDate);
            makeRoyaltyPaymentToRecordLabelQuery.setString(2, songTitle);
            makeRoyaltyPaymentToRecordLabelQuery.setString(3, albumID);

            // Execute the query
            int rowsAffected = makeRoyaltyPaymentToRecordLabelQuery.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out
                        .println("Royalty payment made successfully for song: " + songTitle + ", Album ID: " + albumID);
            } else {
                System.out.println(
                        "No royalty payment made for the specified song, album ID, and record label account ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error making royalty payment: " + e.getMessage());
        }
    }

    // Making Royalty Payment to given Artist
    public static void makeMonthlyRoyaltyPaymentToArtists() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the payment year(YYYY): ");
            String paymentYear = scanner.nextLine();
            System.out.println("Enter payment month(MM): ");
            String paymentMonth = scanner.nextLine();
            String paymentDate = paymentYear + "-" + paymentMonth + "-01";
            System.out.println("Enter song title: ");
            String songTitle = scanner.nextLine();
            System.out.println("Enter album ID: ");
            String albumID = scanner.nextLine();

            if (checkIfArtistPaymentExists(paymentDate, songTitle, albumID)) {
                System.out.println("Payment has been done for the given information.");
                return;
            }
            makeMonthlyRoyaltyPaymentToArtistsQuery.setString(1, paymentDate);
            makeMonthlyRoyaltyPaymentToArtistsQuery.setString(2, albumID);
            makeMonthlyRoyaltyPaymentToArtistsQuery.setString(3, songTitle);

            // Execute the query
            int rowsAffected = makeMonthlyRoyaltyPaymentToArtistsQuery.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out.println("Monthly royalty payment made successfully for song: " + songTitle);
            } else {
                System.out.println("No royalty payment made for the specified song and artists.");
            }

        } catch (SQLException e) {
            System.out.println("Error making monthly royalty payment: " + e.getMessage());
        }
    }

    // Making Royalty Payment to given Podcast Host
    public static void makeMonthlyPaymentToPodcastHost() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the payment year(YYYY): ");
            String paymentYear = scanner.nextLine();
            System.out.println("Enter payment month(MM): ");
            String paymentMonth = scanner.nextLine();
            String paymentDate = paymentYear + "-" + paymentMonth + "-01";
            System.out.println("Enter Podcast Host ID:");
            String podcastHostAccountID = scanner.nextLine();
            if (checkIfPodcastHostPaymentExists(paymentDate, podcastHostAccountID)) {
                System.out.println("Payment has been done for the given information.");
            } else {
	            System.out.println(paymentDate);
	            makeMonthlyPaymentToPodcastHostQuery.setString(1, paymentDate);
	            makeMonthlyPaymentToPodcastHostQuery.setString(2, paymentYear + "-" + paymentMonth + "%");
	            makeMonthlyPaymentToPodcastHostQuery.setString(3, podcastHostAccountID);
	            System.out.println(makeMonthlyPaymentToPodcastHostQuery);
	            // Execute the query
	            int rowsAffected = makeMonthlyPaymentToPodcastHostQuery.executeUpdate();
	
	            // Check if any rows were affected
	            if (rowsAffected > 0) {
	                System.out.println("Monthly Payment to the PodcastHost for each released episode was successful");
	            } else {
	                System.out.println("No Monthly Payment to the PodcastHost");
	            }
            }

        } catch (SQLException e) {
            System.out.println("Error making monthly royalty payment: " + e.getMessage());
        }
    }
    
    // checking if the Podcast host payment exists
    private static boolean checkIfPodcastHostPaymentExists(String paymentDate, String podcastHostAccountID) {
        try {

            getPodcastHostPaymentForTheDateQuery.setString(1, podcastHostAccountID);
            getPodcastHostPaymentForTheDateQuery.setString(2, paymentDate);

            System.out.println(getPodcastHostPaymentForTheDateQuery);
            result = getPodcastHostPaymentForTheDateQuery.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Reporting total revenue for the given month
    public static void getTotalRevenuePerMonth() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the payment year(YYYY): ");
        String paymentYear = scanner.nextLine();
        System.out.println("Enter payment month(MM): ");
        String paymentMonth = scanner.nextLine();
        String paymentDate = paymentYear + "-" + paymentMonth + "%";
        try {
            getTotalRevenue.setString(1, paymentDate);
            result = getTotalRevenue.executeQuery();
            result.next();
            System.out.println("Revenue for the given year and month is " + result.getString("payment"));
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
    
    // Reporting total revenue for the given year
    public static void getTotalRevenuePerYear() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the payment year(YYYY): ");
        String paymentYear = scanner.nextLine();
        String paymentDate = paymentYear + "%";
        try {
            getTotalRevenue.setString(1, paymentDate);
            result = getTotalRevenue.executeQuery();
            result.next();
            System.out.println("Revenue for the given year is " + result.getString("payment"));
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // ------------------------------------------------------------------------------------------------
    // REPORTS
    // ------------------------------------------------------------------------------------------------

    // Menu for Report
    public static void showReportMenu() {
        while (true) {
            System.out.println("\nReport Menu");
            System.out.println("1. Get Monthly Song Play Count");
            System.out.println("2. Get Monthly Album Play Count");
            System.out.println("3. Get Monthly Artist Play Count");
            System.out.println("4. Get Total Artist Payment");
            System.out.println("5. Get Total Host Payment");
            System.out.println("6. Get Total Label Payment");
            System.out.println("7. Get Total Revenue Per Month");
            System.out.println("8. Get Total Revenue Per Year");
            System.out.println("9. Get Songs from Artist");
            System.out.println("10. Get Songs from Album");
            System.out.println("11. Get Episodes from Podcast");
            System.out.println("0. Go back to main menu");
            System.out.println("Enter Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    getMonthlyPlayCountPerSong();
                    break;
                case "2":
                    getMonthlyPlayCountPerAlbum();
                    break;
                case "3":
                    getMonthlyPlayCountPerArtist();
                    break;
                case "4":
                    getTotalPaymentsToArtist();
                    break;
                case "5":
                    getTotalPaymentsToHost();
                    break;
                case "6":
                    getTotalPaymentsToRecordLabel();
                    break;
                case "7":
                    getTotalRevenuePerMonth();
                    break;
                case "8":
                    getTotalRevenuePerYear();
                    break;
                case "9":
                    reportAllSongsByArtist();
                    break;
                case "10":
                    reportAllSongsByAlbumID();
                    break;
                case "11":
                    getEpisodesFromPodcast();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Total payment given the host ID
    private static void getTotalPaymentsToHost() {
        try {
            // Get user inputs for start and end dates
            System.out.println("Total Payments made to Host for a given Period");
            scanner = new Scanner(System.in);
            System.out.println("Enter podcastHostAccountID: ");
            String podcastHostAccountID = scanner.nextLine();
            System.out.println("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();

            totalPaymentsToPodcastHostQuery.setString(1, startDate);
            totalPaymentsToPodcastHostQuery.setString(2, endDate);
            totalPaymentsToPodcastHostQuery.setString(3, podcastHostAccountID);
            result = totalPaymentsToPodcastHostQuery.executeQuery();

            if (!result.next()) {
                System.out.println("No payment data found for the specified podcast host account ID.");
                return;
            }

            System.out.println("Total Payments to Podcast Host:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error displaying total payments to Podcast Host: " + e.getMessage());
        }
    }

    // Given song ID, get monthly play count
    public static void getMonthlyPlayCountPerSong() {
        scanner = new Scanner(System.in);
        System.out.println("Enter albumID");
        String albumID = scanner.nextLine();
        System.out.println("Enter song title");
        String songTitle = scanner.nextLine();
        try {
            getMonthlyPlayCountPerSongQuery.setString(1, albumID);
            getMonthlyPlayCountPerSongQuery.setString(2, songTitle);
            result = getMonthlyPlayCountPerSongQuery.executeQuery();

            if (!result.next()) {
                System.out.println("No play count data found for the specified album ID and song title.");
                return;
            }

            System.out.println("Monthly Play Count per Song:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error getting monthly play count per song: " + e.getMessage());
        }
    }

    // Given Album ID, get monthly play count
    public static void getMonthlyPlayCountPerAlbum() {
        scanner = new Scanner(System.in);
        System.out.println("Enter albumID:");
        String albumID = scanner.nextLine();

        try {
            getMonthlyPlayCountPerAlbumQuery.setString(1, albumID);
            result = getMonthlyPlayCountPerAlbumQuery.executeQuery();

            if (!result.next()) {
                System.out.println("No play count data found for the specified album ID.");
                return;
            }

            System.out.println("Monthly Play Count per Album:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error getting monthly play count per album: " + e.getMessage());
        }
    }

    // Given Artist ID, get monthly play count
    public static void getMonthlyPlayCountPerArtist() {
        scanner = new Scanner(System.in);
        System.out.println("Enter artistID:");
        String artistID = scanner.nextLine();

        try {
            getMonthlyPlayCountPerArtistQuery.setString(1, artistID);
            result = getMonthlyPlayCountPerArtistQuery.executeQuery();

            if (!result.next()) {
                System.out.println("No play count data found for the specified artist ID.");
                return;
            }

            System.out.println("Monthly Play Count per Artist:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error getting monthly play count per artist: " + e.getMessage());
        }

    }

    // Given Artist ID, get total payment for given time period
    public static void getTotalPaymentsToArtist() {
        try {
            // Get user inputs for start and end dates
            scanner = new Scanner(System.in);
            System.out.println("Enter artistAccountID: ");
            String artistAccountID = scanner.nextLine();
            System.out.println("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();

            totalPaymentsToArtistQuery.setString(1, startDate);
            totalPaymentsToArtistQuery.setString(2, endDate);
            totalPaymentsToArtistQuery.setString(3, artistAccountID);
            result = totalPaymentsToArtistQuery.executeQuery();

            if (!result.next()) {
                System.out.println("No payment data found for the specified artist account ID.");
                return;
            }

            System.out.println("Total Payments to Artist:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error displaying total payments to artist: " + e.getMessage());
        }
    }

    // Given Record Label ID, get total payment for given time period
    public static void getTotalPaymentsToRecordLabel() {
        try {
            // Get user inputs for artistAccountID, start date, and end date
            scanner = new Scanner(System.in);
            System.out.println("Enter recordLabelAccountID: ");
            String recordLabelAccountID = scanner.nextLine();
            System.out.println("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();

            // Set the parameters and execute the SQL query
            totalPaymentsToLabelQuery.setString(1, startDate);
            totalPaymentsToLabelQuery.setString(2, endDate);
            totalPaymentsToLabelQuery.setString(3, recordLabelAccountID);
            result = totalPaymentsToLabelQuery.executeQuery();

            // Display the results
            if (!result.next()) {
                System.out.println("No payment data found for the specified record label account ID.");
                return;
            }

            System.out.println("Total Payments to Record Label:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error displaying total payments to record label: " + e.getMessage());
        }
    }

    // Given artist ID, report all songs
    public static void reportAllSongsByArtist() {
        try {
            // Get user input for artistAccountID
            scanner = new Scanner(System.in);
            System.out.println("Enter artistAccountID: ");
            String artistAccountID = scanner.nextLine();

            // Set the parameter and execute the SQL query
            reportAllSongsByArtistQuery.setString(1, artistAccountID);
            reportAllSongsByArtistQuery.setString(2, artistAccountID);
            result = reportAllSongsByArtistQuery.executeQuery();

            // Display the results
            if (!result.next()) {
                System.out.println("No Songs found for the given artist.");
                return;
            }

            System.out.println("Songs by Artist:");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error reporting all songs by artist: " + e.getMessage());
        }
    }

    // Given album ID, report all songs
    public static void reportAllSongsByAlbumID() {
        try {
            // Get user input for artistAccountID
            scanner = new Scanner(System.in);
            System.out.println("Enter album ID: ");
            String albumID = scanner.nextLine();

            reportAllSongsByAlbumQuery.setString(1, albumID);

            // Execute the query and get the result set
            result = reportAllSongsByAlbumQuery.executeQuery();

            // Check if any rows were returned
            if (!result.next()) {
                System.out.println("No songs found for the specified album ID.");
                return;
            }

            // Display the songs
            System.out.println("Songs in Album ID " + albumID + ":");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Error reporting songs by album ID: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------------------------------------
    // PODCAST
    // ----------------------------------------------------------------------------------------------------------

    // Menu for Podcast
    public static void showPodcastMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPodcast Menu");
            System.out.println("1. Add new Podcast");
            System.out.println("2. Update Podcast");
            System.out.println("3. Delete Podcast");
            System.out.println("4. Add Genres to Podcast");
            System.out.println("5. Remove Genres to Podcast");
            System.out.println("6. List all Podcasts");
            System.out.println("7. List existing Podcast Genres");
            System.out.println("8. Show Sponsors Menu");
            System.out.println("9. Show Special Guests Menu");
            System.out.println("10. List all Podcast Subscribers");
            System.out.println("11. Show Hosts Relation Menu");
            System.out.println("0. Go back to main menu");
            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertPodcastMenu();
                    break;
                case "2":
                    updatePodcastMenu();
                    break;
                case "3":
                    deletePodcast();
                    break;
                case "4":
                    addGenresToPodcast();
                    break;
                case "5":
                    deleteGenresFromPodcast();
                    break;
                case "6":
                    displayItems(getAllPodcastsQuery);
                    break;
                case "7":
                    displayItems(getAllPodcastGenresQuery);
                    break;
                case "8":
                    showSponsorMenu();
                    break;
                case "9":
                    showSplGuestMenu();
                    break;
                case "10":
                    displayItems(getAllPodcastSubscribersQuery);
                    break;
                case "11":
                    showHostsMenu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Displaying all the items
    public static void displayItems(PreparedStatement query) {
        System.out.println(" The Database contains : ");
        try {
            result = query.executeQuery();
            if (!result.next()) {
                System.out.println("No rows exist in this table");
                return;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println("Failure");
        }

    }

    // Add genre to the given podcast
    public static void addGenresToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter all Genres (ex: Rock, Pop)");
        String genres = scanner.nextLine();
        String[] genreList = genres.split(",");
        try {
            connection.setAutoCommit(false);
            for (String genre : genreList) {
                try {
                    insertPodcastGenreQuery.setString(1, genre.strip());
                    insertPodcastGenreQuery.setString(2, podcastID);
                    insertPodcastGenreQuery.executeUpdate();
                    connection.commit();
                    System.out.println("Insertion Successful");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    connection.rollback();
                    System.out.println("Operation Failed. Try Again with Valid Inputs");
                } finally {
                    connection.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    //Display all the Genre for the given podcast
    public static void displayAllPodcastGenres(String podcastID) {
        try {
            getPodcastGenreQuery.setString(1, podcastID);
            result = getPodcastGenreQuery.executeQuery();
            if (!result.next()) {
                System.out.println("No Such Podcast exists");
                return;
            }
            System.out.println("Genres of the Podcast : ");
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
        }

    }

    //Delete Genre for the given podcast
    public static void deleteGenresFromPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        displayAllPodcastGenres(podcastID);

        System.out.println("Enter all Genres to delete (ex: Rock, Pop)");
        String genres = scanner.nextLine();
        String[] genreList = genres.split(",");
        try {
            connection.setAutoCommit(false);
            try {
                for (String genre : genreList) {
                    deletePodcastGenreQuery.setString(1, genre.strip());
                    deletePodcastGenreQuery.setString(2, podcastID);
                    deletePodcastGenreQuery.executeUpdate();
                }
                connection.commit();
                System.out.println("Deletion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    //Menu for inserting a new podcast
    public static void insertPodcastMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcastID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter Podcast Name");
        String podcastName = scanner.nextLine();
        System.out.println("Enter language");
        String language = scanner.nextLine();
        System.out.println("Enter country");
        String country = scanner.nextLine();
        System.out.println("Enter episode count");
        int episodeCount = scanner.nextInt();
        scanner.nextLine();
        int rating = 0;
        int totalSubscribers = 0;
        // Call the insertPodcast() function with the provided inputs
        insertPodcast(podcastID, podcastName, language, country, episodeCount, rating, totalSubscribers);
    }

    // Insert a new podcast
    public static void insertPodcast(String podcastID, String podcastName, String language, String country,
            int episodeCount, int rating, int totalSubscribers) {
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                insertPodcastQuery.setString(1, podcastID);
                insertPodcastQuery.setString(2, podcastName);
                insertPodcastQuery.setString(3, language);
                insertPodcastQuery.setString(4, country);
                insertPodcastQuery.setInt(5, episodeCount);
                insertPodcastQuery.setInt(6, rating);
                insertPodcastQuery.setInt(7, totalSubscribers);

                // Execute the query
                insertPodcastQuery.executeUpdate();
                connection.commit();
                System.out.println("Insertion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // delete existing podcast
    public static void deletePodcast() {
        String podcastID;
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        podcastID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deletePodcastQuery.setString(1, podcastID);
                deletePodcastQuery.executeUpdate();
                connection.commit();
                System.out.println("Deletion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Update the given podcast
    public static void updatePodcastRating(String podcastID) {
        try {
            getAvgRatingPodcastSubscriberQuery.setString(1, podcastID);
            ResultSet rs = getAvgRatingPodcastSubscriberQuery.executeQuery();
            rs.next();
            updatePodcastRatingQuery.setDouble(1, rs.getDouble("avgRating"));
            updatePodcastRatingQuery.setString(2, podcastID);
            updatePodcastRatingQuery.executeUpdate();
            rs.close();
            System.out.println("Updation Successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed.");
        }
    }

    // menu for updating podcast 
    public static void updatePodcastMenu() {
        String value;
        String podcastID;
        String option;
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        podcastID = scanner.nextLine();
        System.out.println("1. Update podcast name");
        System.out.println("2. Update podcast language");
        System.out.println("3. Update podcast country");
        System.out.println("4. Update podcast episode count");
        System.out.println("5. Update podcast rating");
        System.out.println("6. Update podcast total subscribers");
        option = scanner.nextLine();
        System.out.println("Enter the new value");
        value = scanner.nextLine();

        updatePodcast(podcastID, option, value);
    }

    // Update the given podcast
    public static void updatePodcast(String podcastID, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updatePodcastNameQuery.setString(1, newValue);
                        updatePodcastNameQuery.setString(2, podcastID);
                        updatePodcastNameQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    case "2":
                        updatePodcastLanguageQuery.setString(1, newValue);
                        updatePodcastLanguageQuery.setString(2, podcastID);
                        updatePodcastLanguageQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    case "3":
                        updatePodcastCountryQuery.setString(1, newValue);
                        updatePodcastCountryQuery.setString(2, podcastID);
                        updatePodcastCountryQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    case "4":
                        updatePodcastEpisodeCountQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastEpisodeCountQuery.setString(2, podcastID);
                        updatePodcastEpisodeCountQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    case "5":
                        updatePodcastRatingQuery.setDouble(1, Double.parseDouble(newValue));
                        updatePodcastRatingQuery.setString(2, podcastID);
                        updatePodcastRatingQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    case "6":
                        updatePodcastTotalSubscribersQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastTotalSubscribersQuery.setString(2, podcastID);
                        updatePodcastTotalSubscribersQuery.executeUpdate();
                        System.out.println("Update successful");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // ----------------------------------------------------------------------------------------------------------
    // PODAST HOST
    // ----------------------------------------------------------------------------------------------------------

    // Menu for Podcast Host
    public static void showPodcastHostMenu() throws SQLException {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPodcast Host Menu");
            System.out.println("1. Add new Podcast Host");
            System.out.println("2. Update Podcast Host");
            System.out.println("3. Delete Podcast Host");
            System.out.println("4. Add Podcast Host to Podcast");
            System.out.println("5. List All Podcast Hosts ");
            System.out.println("0. Go back to main menu");

            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertPodcastHostMenu();
                    break;
                case "2":
                    updatePodcastHostMenu();
                    break;
                case "3":
                    deletePodcastHost();
                    break;
                case "4":
                    addPodcastHostToPodcast();
                    break;
                case "5":
                    displayItems(getAllPodcastHostsQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Menu for inserting new podcast host
    public static void insertPodcastHostMenu() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast Host AccountID");
        String podcastHostAccountID = scanner.nextLine();
        System.out.println("Enter account password");
        String password = scanner.nextLine();
        System.out.println("Enter first Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter phone");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter city");
        String city = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            if (insertAccount(podcastHostAccountID, "Podcast Host", password) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            if (insertPodcastHost(podcastHostAccountID, firstName, lastName, phone, email, city) == -1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }
            System.out.println("Podcast Host inserted successfully!");
            connection.commit();
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Insert new podcast host
    public static int insertPodcastHost(String podcastHostAccountID, String firstName, String lastName, int phone,
            String email, String city) throws SQLException {
        try {

            // Set the parameters
            insertPodcastHostQuery.setString(1, podcastHostAccountID);
            insertPodcastHostQuery.setString(2, firstName);
            insertPodcastHostQuery.setString(3, lastName);
            insertPodcastHostQuery.setInt(4, phone);
            insertPodcastHostQuery.setString(5, email);
            insertPodcastHostQuery.setString(6, city);

            // Execute the query
            insertPodcastHostQuery.executeUpdate();

            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
        return -1;
    }

    // Delete podcast host given ID
    public static void deletePodcastHost() {
        String podcastHostID;
        System.out.println("Enter podcast Host ID:");
        podcastHostID = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                deleteAccount(podcastHostID);
                connection.commit();
                System.out.println("Deletion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // get host from ID
    public static int getPodcastHostByPodcastHostID(String podcastHostAccountID) {
        try {
            getPodcastHostbyPodcastHostIDQuery.setString(1, podcastHostAccountID);
            result = getPodcastHostbyPodcastHostIDQuery.executeQuery();
            if (!result.next()) {
                return -1;
            }
            result.beforeFirst();
            display_table(result);
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
            return -1;
        }
        return 0;
    }
    
    // Menu for updating podcast host
    public static void updatePodcastHostMenu() {

        scanner = new Scanner(System.in);
        System.out.println("Enter podcast host account ID:");
        String podcastHostAccountID = scanner.nextLine();
        if (getPodcastHostByPodcastHostID(podcastHostAccountID) == -1) {
            System.out.println("Podcats Host not found. Please try again!");
            return;
        }
        System.out.println("1. Update podcast Host first name");
        System.out.println("2. Update podcast Host last name");
        System.out.println("3. Update podcast Host phone");
        System.out.println("4. Update podcast Host email");
        System.out.println("5. Update podcast Host city");
        String option = scanner.nextLine();
        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        updatePodcastHost(podcastHostAccountID, option, newValue);
    }

    // Update given podcast host
    public static void updatePodcastHost(String podcastHostAccountID, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updatePodcastHostFirstNameQuery.setString(1, newValue);
                        updatePodcastHostFirstNameQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostFirstNameQuery.executeUpdate();
                        break;

                    case "2":
                        updatePodcastHostLastNameQuery.setString(1, newValue);
                        updatePodcastHostLastNameQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostLastNameQuery.executeUpdate();
                        break;

                    case "3":
                        updatePodcastHostPhoneQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastHostPhoneQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostPhoneQuery.executeUpdate();
                        break;

                    case "4":
                        updatePodcastHostEmailQuery.setString(1, newValue);
                        updatePodcastHostEmailQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostEmailQuery.executeUpdate();
                        break;

                    case "5":
                        updatePodcastHostCityQuery.setString(1, newValue);
                        updatePodcastHostCityQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostCityQuery.executeUpdate();
                        break;

                    default:
                        System.out.println("Wrong choice. Select from given numbers");
                        break;
                }
                connection.commit();
                System.out.println("Insertion Successful");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Host
    // ----------------------------------------------------------------------------------------------------------
    
    //Menu for podcast host
    public static void showHostsMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHosts Relation Menu");
            System.out.println("1. Add new Podcast Host to Podcast");
            System.out.println("2. Update hosts information");
            System.out.println("3. Delete Podcast Host from Podcast");
            System.out.println("4. List all hosts relation table.");
            System.out.println("0. Go back to main menu");
            System.out.println("Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    addPodcastHostToPodcast();
                    break;
                case "2":
                    updateHostMenu();
                    break;
                case "3":
                    deletePodcastHostFromPodcast();
                    break;
                case "4":
                    displayItems(getAllHostsQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // add podcast host to given podcast
    public static void addPodcastHostToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast host ID");
        String podcastHostAccountID = scanner.nextLine();
        System.out.println("Enter podcast ID");
        String podcastHostID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                insertHostsQuery.setString(1, podcastHostAccountID);
                insertHostsQuery.setString(2, podcastHostID);
                insertHostsQuery.executeUpdate();
                connection.commit();
                System.out.println("Insertion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Get host information given ID
    private static int getHostByID(String podcastHostAccountID, String podcastID) {
        try {
            selectHostsQuery.setString(1, podcastHostAccountID);
            selectHostsQuery.setString(2, podcastID);
            result = selectHostsQuery.executeQuery();
            result.beforeFirst();
            display_table(result);
            System.out.println();
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failure");
            return -1;
        }
    }

    // Menu for updating host
    private static void updateHostMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter Podcast Host Account ID:");
        String podcastHostAccountID = scanner.nextLine();
        System.out.println("Enter Podcast ID:");
        String podcastID = scanner.nextLine();
        if (getHostByID(podcastHostAccountID, podcastID) == -1) {
            System.out.println("Given Host has never hosted in the given Podcast not found. Please try again!");
            return;
        }
        System.out.println("1. Update Podcast host Account ID");
        System.out.println("2. Update Podcast ID");
        String option = scanner.nextLine();
        System.out.println("Enter the new value:");
        String newValue = scanner.nextLine();

        updateHost(podcastHostAccountID, podcastID, option, newValue);

    }

    // Update the information of a given podcast host
    private static void updateHost(String podcastHostAccountID, String podcastID, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1": {
                        updatePodcastHostAccountIDHostsQuery.setString(1, newValue);
                        updatePodcastHostAccountIDHostsQuery.setString(2, podcastHostAccountID);
                        updatePodcastHostAccountIDHostsQuery.setString(3, podcastID);
                        updatePodcastHostAccountIDHostsQuery.executeUpdate();
                        System.out.println("Update Successful");
                    }
                    case "2": {
                        updatePodcastIDHostsQuery.setString(1, newValue);
                        updatePodcastIDHostsQuery.setString(2, podcastHostAccountID);
                        updatePodcastIDHostsQuery.setString(3, podcastID);
                        updatePodcastIDHostsQuery.executeUpdate();
                        System.out.println("Update Successful");
                    }
                    default:
                        System.out.println("Cannot perform the update operation");
                }
                System.out.println("Release details updated successfully!");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }

    }

    // Delete Podcast hosts from the given podcast
    private static void deletePodcastHostFromPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter Podcast Host Account ID:");
        String podcastHostAccountID = scanner.nextLine();
        System.out.println("Enter Podcast ID:");
        String podcastID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deleteHostsQuery.setString(1, podcastHostAccountID);
                deleteHostsQuery.setString(2, podcastID);
                deleteHostsQuery.executeUpdate();
                connection.commit();
                System.out.println("Deletion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }

    }

    // Show all the operations related to the Podcast episodes
    public static void showPodcastEpisodeMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPodcast Episode Menu");
            System.out.println("1. Add new Podcast Episode");
            System.out.println("2. Update Podcast Episode");
            System.out.println("3. Delete Podcast Episode");
            System.out.println("4. Add Podcast Episode to podcast");
            System.out.println("5. List All Podcast Episodes");
            System.out.println("0. Go back to main menu");
            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertPodcastEpisodeMenu();
                    break;
                case "2":
                    updatePodcastEpisodeMenu();
                    break;
                case "3":
                    deletePodcastEpisode();
                    break;
                case "4":
                    addPodcastEpisodeToPodcast();
                    break;
                case "5":
                    displayItems(getAllPodcastEpisodesQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Function to add an episode to the given podcast
    public static void addPodcastEpisodeToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter Episode title");
        String episodeTitle = scanner.nextLine();
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter new podcast ID");
        String newValue = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                addPodcastEpisodeToPodcastQuery.setString(1, newValue);
                addPodcastEpisodeToPodcastQuery.setString(2, podcastID);
                addPodcastEpisodeToPodcastQuery.setString(3, episodeTitle);

                int rowsAffected = addPodcastEpisodeToPodcastQuery.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Podcast Episode added to Podcast successfully!");
                } else {
                    System.out.println("Operation Failed. Unable to find specified Podcast");
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Menu for inserting a podcast episode
    public static void insertPodcastEpisodeMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter Episode Title");
        String episodeTitle = scanner.nextLine();
        System.out.println("Enter duration(in minutes)");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter release Date in YYYY-MM-DD format");
        String releaseDate = scanner.nextLine();
        System.out.println("Enter listening count");
        int listeningCount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter advertisement count");
        int advertisementCount = scanner.nextInt();
        scanner.nextLine();

        // Call the insertPodcast() function with the provided inputs
        insertPodcastEpisode(episodeTitle, podcastID, duration, releaseDate, listeningCount, advertisementCount);
    }

    // Insert an episode into a given podcast
    public static void insertPodcastEpisode(String episodeTitle, String podcastID, int duration, String releaseDate,
            int listeningCount, int advertisementCount) {
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                insertPodcastEpisodeQuery.setString(1, episodeTitle);
                insertPodcastEpisodeQuery.setString(2, podcastID);
                insertPodcastEpisodeQuery.setInt(3, duration);
                insertPodcastEpisodeQuery.setString(4, releaseDate);
                insertPodcastEpisodeQuery.setInt(5, listeningCount);
                insertPodcastEpisodeQuery.setInt(6, advertisementCount);

                // Execute the query
                insertPodcastEpisodeQuery.executeUpdate();
                connection.commit();
                System.out.println("Insertion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Show operations related to the podcast episodes
    public static void updatePodcastEpisodeMenu() {
        String value;
        String podcastID;
        String episodeTitle;
        String option;
        scanner = new Scanner(System.in);
        System.out.println("Enter podcastID:");
        podcastID = scanner.nextLine();
        System.out.println("Enter episode title:");
        episodeTitle = scanner.nextLine();
        System.out.println("1. Update Duration");
        System.out.println("2. Update Release Date");
        System.out.println("3. Update Listening Count");
        System.out.println("4. Update Advertisement Count");
        option = scanner.nextLine();
        System.out.println("Enter the new value");
        value = scanner.nextLine();

        updatePodcastEpisode(podcastID, episodeTitle, option, value);
    }

    // Update the information in the given podcast episode
    public static void updatePodcastEpisode(String podcastID, String episodeTitle, String option, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (option) {
                    case "1":
                        updatePodcastEpisodeDurationQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastEpisodeDurationQuery.setString(2, podcastID);
                        updatePodcastEpisodeDurationQuery.setString(3, episodeTitle);
                        updatePodcastEpisodeDurationQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "2":
                        updatePodcastEpisodeReleaseDateQuery.setString(1, newValue);
                        updatePodcastEpisodeReleaseDateQuery.setString(2, podcastID);
                        updatePodcastEpisodeReleaseDateQuery.setString(3, episodeTitle);
                        updatePodcastEpisodeReleaseDateQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "3":
                        updatePodcastEpisodeListeningCountQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastEpisodeListeningCountQuery.setString(2, podcastID);
                        updatePodcastEpisodeListeningCountQuery.setString(3, episodeTitle);
                        updatePodcastEpisodeListeningCountQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    case "4":
                        updatePodcastEpisodeAdvertisementCountQuery.setInt(1, Integer.parseInt(newValue));
                        updatePodcastEpisodeAdvertisementCountQuery.setString(2, podcastID);
                        updatePodcastEpisodeAdvertisementCountQuery.setString(3, episodeTitle);
                        updatePodcastEpisodeAdvertisementCountQuery.executeUpdate();
                        System.out.println("Update Successful");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again");
                        break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete the given episode from the podcast
    public static void deletePodcastEpisode() {
        String podcastID;
        String episodeTitle;
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID:");
        podcastID = scanner.nextLine();
        System.out.println("Enter episode Title:");
        episodeTitle = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            try {
                deletePodcastEpisodeQuery.setString(1, podcastID);
                deletePodcastEpisodeQuery.setString(2, episodeTitle);
                deletePodcastEpisodeQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Update the subscriber count for the given podcast
    public static void updateSubscriberCount(String ID, PreparedStatement getCountStatement, PreparedStatement updateStatement){
        try {
            getCountStatement.setString(1, ID);
            ResultSet rs = getCountStatement.executeQuery();
            rs.next();
            updateStatement.setDouble(1, rs.getDouble("count"));
            updateStatement.setString(2, ID);
            updateStatement.executeUpdate();
            rs.close();
            System.out.println("Updation of Subscriber Count Successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed.");
        }
    }

    // Subsribe a user to the given podcast
    public static void subscribeUserToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter User ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        int rating = 0;
        try {
            connection.setAutoCommit(false);
            try {
                insertPodcastSubscriberQuery.setString(1, podcastID);
                insertPodcastSubscriberQuery.setString(2, userAccountID);
                insertPodcastSubscriberQuery.setInt(3, rating);
                insertPodcastSubscriberQuery.executeUpdate();
                System.out.println("User successfully subscribed to the given podcast");
                connection.commit();
                //updateSubscriberCount(podcastID, countSubscribersForPodcast, updatePodcastTotalSubscribersQuery);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Unsubsribe a user from the given podcast
    public static void unsubscribeUserFromPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter User ID");
        String userAccountID = scanner.nextLine();
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                deletePodcastSubscriberQuery.setString(1, podcastID);
                deletePodcastSubscriberQuery.setString(2, userAccountID);
                deletePodcastSubscriberQuery.executeUpdate();
                System.out.println("User successfully unsubscribed to the given podcast");
                connection.commit();
                //updateSubscriberCount(podcastID, countSubscribersForPodcast, updatePodcastTotalSubscribersQuery);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Show the operations related to sponsors
    public static void showSponsorMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSponsor Menu");
            System.out.println("1. Add new Sponsor");
            System.out.println("2. Delete Sponsor");
            System.out.println("3. Add Sponsor to Podcast");
            System.out.println("4. Remove Sponsor from Podcast");
            System.out.println("5. Update Sponsor Amount");
            System.out.println("6. List All Sponsors");
            System.out.println("7. List All Affiliations");
            System.out.println("0. Go back to main menu");
            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertSponsor();
                    break;
                case "2":
                    deleteSponsor();
                    break;
                case "3":
                	addSponsorToPodcast();
                    break;
                case "4":
                    deleteSponsorForPodcast();
                    break;
                case "5":
                    updateSponsorAmountForPodcast();
                    break;
                case "6":
                    displayItems(getAllSponsorsQuery);
                    break;
                case "7":
                    displayItems(getAllAffiliatedWithQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Insert a sponsor into the database
    public static void insertSponsor() {
        scanner = new Scanner(System.in);
        System.out.println("Enter sponsor ID");
        String sponsorID = scanner.nextLine();
        System.out.println("Enter sponsor Name");
        String sponsorName = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                insertSponsorQuery.setString(1, sponsorID);
                insertSponsorQuery.setString(2, sponsorName);
                insertSponsorQuery.executeUpdate();
                connection.commit();
                System.out.println("Insertion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete the given sponsor from the database
    public static void deleteSponsor() {
        scanner = new Scanner(System.in);
        System.out.println("Enter sponsor ID");
        String sponsorID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                deleteSponsorQuery.setString(1, sponsorID);
                deleteSponsorQuery.executeUpdate();
                connection.commit();
                System.out.println("Deletion Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Add the given sponsor to the podcast
    public static void addSponsorToPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter sponsor ID");
        String sponsorID = scanner.nextLine();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                insertAffiliatedWithQuery.setString(1, podcastID);
                insertAffiliatedWithQuery.setString(2, sponsorID);
                insertAffiliatedWithQuery.setDouble(3, amount);
                insertAffiliatedWithQuery.executeUpdate();
                connection.commit();
                System.out.println("Insert Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Update Sponsor amount for the given podcast
    public static void updateSponsorAmountForPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter sponsor ID");
        String sponsorID = scanner.nextLine();
        System.out.println("Enter new amount to update:");
        double amount = scanner.nextDouble();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                updateAffiliatedWithAmountQuery.setString(2, podcastID);
                updateAffiliatedWithAmountQuery.setString(3, sponsorID);
                updateAffiliatedWithAmountQuery.setDouble(1, amount);
                updateAffiliatedWithAmountQuery.executeUpdate();
                connection.commit();
                System.out.println("Update Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete the given sponsor from the given podcast
    public static void deleteSponsorForPodcast() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter sponsor ID");
        String sponsorID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                deleteAffiliatedWithQuery.setString(1, podcastID);
                deleteAffiliatedWithQuery.setString(2, sponsorID);
                deleteAffiliatedWithQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Show operations related to genres
    public static void showGenresMenu(){
        scanner = new Scanner(System.in);
        while(true) {
	        System.out.println("\nGenre Menu");
	        System.out.println("1. Add new Genre");
	        System.out.println("2. Delete Genre");
	        System.out.println("3. List all Genres");
	        System.out.println("0. Go back");
	        System.out.print("\n Enter Choice: ");
	        String choice = scanner.nextLine().strip();
	
	        switch (choice) {
	            case "1":
	                System.out.print("\n Enter Genre name: ");
	                String genre = scanner.nextLine().strip();
	                try {
	                    insertGenreQuery.setString(1,genre);
	                    insertGenreQuery.executeUpdate();
	                    System.out.println("Insertion Successful");
	                } catch (SQLException e) {
	                    System.out.println("Error occurred");
	                }
	                break;
	            case "2":
	                System.out.print("\n Enter Genre name: ");
	                 genre = scanner.nextLine().strip();
	                try {
	                    deleteGenreQuery.setString(1,genre);
	                    deleteGenreQuery.executeUpdate();
	                    System.out.println("Deletion Successful");
	                } catch (SQLException e) {
	                    System.out.println("Error occurred");
	                }
	                break;
	            case "3":
	                displayItems(getAllGenresQuery);
	                break;
	            case "0":
	                return;
	            default:
	                System.out.println("Invalid choice.");
	                break;
	        }
        }
    }

    // Show operations related to special guests
    public static void showSplGuestMenu() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSpecial Guest Menu");
            System.out.println("1. Add new Special Guest");
            System.out.println("2. Delete Special Guest");
            System.out.println("3. Add Special Guest to Podcast Episode");
            System.out.println("4. Remove Special Guest from Podcast Episode");
            System.out.println("5. List All Special Guests");
            System.out.println("6. List All Special Guest Appearances");
            System.out.println("0. Go back to main menu");
            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    insertSplGuest();
                    break;
                case "2":
                    deleteSplGuest();
                    break;
                case "3":
                    addGuestToPodcastEpisode();
                    break;
                case "4":
                    deleteGuestFromPodcastEpisode();
                    break;
                case "5":
                    displayItems(getAllSplGuestsQuery);
                    break;
                case "6":
                    displayItems(getAllInvitesQuery);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Insert a special guest into the database
    public static void insertSplGuest() {
        scanner = new Scanner(System.in);
        System.out.println("Enter guest ID");
        String guestID = scanner.nextLine();
        System.out.println("Enter guest Name");
        String guestName = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {

                // Set the parameters
                insertSplGuestQuery.setString(1, guestID);
                insertSplGuestQuery.setString(2, guestName);
                insertSplGuestQuery.executeUpdate();
                connection.commit();
                System.out.println("Insert Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // delete Special guest from the database
    public static void deleteSplGuest() {
        scanner = new Scanner(System.in);
        System.out.println("Enter guest ID");
        String guestID = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                deleteSplGuestQuery.setString(1, guestID);
                deleteSplGuestQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Add guest to the given podcast episode
    public static void addGuestToPodcastEpisode() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter guest ID");
        String guestID = scanner.nextLine();
        System.out.println("Enter episode Title");
        String episodeTitle = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                insertInvitesQuery.setString(2, podcastID);
                insertInvitesQuery.setString(1, guestID);
                insertInvitesQuery.setString(3, episodeTitle);
                insertInvitesQuery.executeUpdate();
                connection.commit();
                System.out.println("Insert Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // Delete Guest From the given Podcast episode
    public static void deleteGuestFromPodcastEpisode() {
        scanner = new Scanner(System.in);
        System.out.println("Enter podcast ID");
        String podcastID = scanner.nextLine();
        System.out.println("Enter guest ID");
        String guestID = scanner.nextLine();
        System.out.println("Enter episode Title");
        String episodeTitle = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            try {
                // Set the parameters
                deleteInvitesQuery.setString(2, podcastID);
                deleteInvitesQuery.setString(1, guestID);
                deleteInvitesQuery.setString(3, episodeTitle);
                deleteInvitesQuery.executeUpdate();
                connection.commit();
                System.out.println("Delete Successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // -----------------------------------------------------------------------------------------------

    private static void initDBTables() throws SQLException {

        try {
            connection.setAutoCommit(false);

            String accounts_table = "CREATE TABLE IF NOT EXISTS `Account` ( \n" +
                    "`accountID` VARCHAR(50) NOT NULL, \n" +
                    "`type` VARCHAR(50) NOT NULL, \n" +
                    "`password` VARCHAR(100) NOT NULL, \n" +
                    "`registrationDate` DATE NOT NULL, \n" +
                    "PRIMARY KEY(`accountID`) \n" +
                    ");";

            String genres_table = "CREATE TABLE IF NOT EXISTS `Genre` ( \n" +
                    "`genreName` VARCHAR(50), \n" +
                    "PRIMARY KEY(`genreName`) \n" +
                    ");";

            String users_table = "CREATE TABLE IF NOT EXISTS `User` ( \n" +
                    "`userAccountID` VARCHAR(50), \n" +
                    "`firstName` VARCHAR(50) NOT NULL, \n" +
                    "`lastName` VARCHAR(50) NOT NULL, \n" +
                    "`phone` VARCHAR(30) NOT NULL, \n" +
                    "`email` VARCHAR(50) NOT NULL, \n" +
                    "`statusOfSubscription` VARCHAR(30) NOT NULL, \n" +
                    "PRIMARY KEY(`userAccountID`), \n" +
                    "FOREIGN KEY(`userAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String record_labels_table = "CREATE TABLE IF NOT EXISTS `RecordLabel` ( \n" +
                    "`recordLabelAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`labelName` VARCHAR(30) NOT NULL, \n" +
                    "PRIMARY KEY (`recordLabelAccountID`), \n" +
                    "FOREIGN KEY (`recordLabelAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";

            String artists_table = "CREATE TABLE IF NOT EXISTS `Artist` (\n" +
                    "`artistAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`artistName` VARCHAR(50) NOT NULL, \n" +
                    "`status` VARCHAR(50), \n" +
                    "`type` VARCHAR(50), \n" +
                    "`country` VARCHAR(50), \n" +
                    "`primaryGenre` VARCHAR(50), \n" +
                    "`monthlyListeners` INT DEFAULT 0, \n" +
                    "`recordLabelAccountID` VARCHAR(50), \n" +
                    "PRIMARY KEY (`artistAccountID`), \n" +
                    "FOREIGN KEY (`artistAccountID`) REFERENCES \n" +
                    "`Account`(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY (`recordLabelAccountID`) REFERENCES \n" +
                    "`RecordLabel`(`recordLabelAccountID`) ON DELETE SET NULL ON \n" +
                    "UPDATE CASCADE,\n" +
                    "FOREIGN KEY (`primaryGenre`) REFERENCES \n" +
                    "`Genre`(`genreName`) ON DELETE SET NULL ON UPDATE CASCADE);";

            String albums_table = "CREATE TABLE IF NOT EXISTS `Album` ( \n" +
                    "`albumID` VARCHAR(50) NOT NULL, \n" +
                    "`albumName` VARCHAR(255) NOT NULL, \n" +
                    "`releaseYear` INT NOT NULL, \n" +
                    "`edition` VARCHAR(255) NOT NULL, \n" +
                    "PRIMARY KEY (`albumID`) );";

            String releases_table = "CREATE TABLE IF NOT EXISTS `Releases` ( \n" +
                    "`artistAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`albumID` VARCHAR(50) NOT NULL, \n" +
                    "PRIMARY KEY (`artistAccountID`, `albumID`), \n" +
                    "FOREIGN KEY (`artistAccountID`) REFERENCES `Artist` \n" +
                    "(`artistAccountID`) ON UPDATE CASCADE ON DELETE CASCADE, \n" +
                    "FOREIGN KEY (`albumID`) REFERENCES `Album` (`albumID`) ON \n" +
                    "UPDATE CASCADE ON DELETE CASCADE );";

            String songs_table = "CREATE TABLE IF NOT EXISTS `Song` ( \n" +
                    "`albumID` VARCHAR(50) NOT NULL, \n" +
                    "`songTitle` VARCHAR(50) NOT NULL, \n" +
                    "`duration` INT NOT NULL, \n" +
                    "`playCount` INT NOT NULL, \n" +
                    "`releaseDate` DATE NOT NULL, \n" +
                    "`releaseCountry` VARCHAR(50) NOT NULL, \n" +
                    "`language` VARCHAR(50) NOT NULL, \n" +
                    "`royaltyRate` DECIMAL(5,2) NOT NULL, \n" +
                    "`trackNumber` INT NOT NULL, \n" +
                    "`mainArtist` VARCHAR(50), \n" +
                    "PRIMARY KEY (`albumID`, `songTitle`), \n" +
                    "FOREIGN KEY (`albumID`) REFERENCES `Album`(`albumID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY (`mainArtist`) REFERENCES \n" +
                    "`Account`(`accountID`) ON DELETE SET NULL ON UPDATE CASCADE \n" +
                    ");";

            String song_genres_table = "CREATE TABLE IF NOT EXISTS `SongGenre` ( \n" +
                    "`genreName` VARCHAR(50) NOT NULL, \n" +
                    "`albumID` VARCHAR(50) NOT NULL, \n" +
                    "`songTitle` VARCHAR(50) NOT NULL, \n" +
                    "PRIMARY KEY (`genreName`, `albumID`, `songTitle`), \n" +
                    "FOREIGN KEY (`genreName`) REFERENCES `Genre`(`genreName`) \n" +
                    "ON DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY (`albumID`, `songTitle`) REFERENCES \n" +
                    "`Song`(`albumID`, `songTitle`) \n" +
                    ");";

            String collaborators_table = "CREATE TABLE IF NOT EXISTS `Collaborators` ( \n" +
                    "`artistAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`albumID` VARCHAR(50) NOT NULL, \n" +
                    "`songTitle` VARCHAR(50) NOT NULL, \n" +
                    "PRIMARY KEY (`artistAccountID`, `albumID`, `songTitle`), \n" +
                    "FOREIGN KEY (`artistAccountID`) REFERENCES \n" +
                    "`Artist`(`artistAccountID`) ON DELETE CASCADE ON UPDATE \n" +
                    "CASCADE, \n" +
                    "FOREIGN KEY (`albumID`, `songTitle`) REFERENCES \n" +
                    "`Song`(`albumID`, `songTitle`) \n" +
                    ");";

            String artist_subscribers_table = "CREATE TABLE IF NOT EXISTS `ArtistSubscribers` ( \n" +
                    "`artistAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`userAccountID` VARCHAR(50) NOT NULL, \n" +
                    "PRIMARY KEY (`artistAccountID`, `userAccountID`), \n" +
                    "FOREIGN KEY (`artistAccountID`) REFERENCES \n" +
                    "`Artist`(`artistAccountID`) ON DELETE CASCADE ON UPDATE \n" +
                    "CASCADE, \n" +
                    "FOREIGN KEY (`userAccountID`) REFERENCES \n" +
                    "`User`(`userAccountID`) ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String podcasts_table = "CREATE TABLE IF NOT EXISTS `Podcast` ( \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`podcastName` VARCHAR(200) NOT NULL, \n" +
                    "`language` VARCHAR(10) NOT NULL, \n" +
                    "`country` VARCHAR(20) NOT NULL, \n" +
                    "`episodeCount` INT NOT NULL, \n" +
                    "`rating` DECIMAL(10,2), \n" +
                    "`totalSubscribers` INT, \n" +
                    "PRIMARY KEY(`podcastID`) \n" +
                    ");";

            String podcast_hosts_table = "CREATE TABLE IF NOT EXISTS `PodcastHost` ( \n" +
                    "`podcastHostAccountID` VARCHAR(50), \n" +
                    "`firstName` VARCHAR(50) NOT NULL, \n" +
                    "`lastName` VARCHAR(50) NOT NULL, \n" +
                    "`phone` VARCHAR(30) NOT NULL, \n" +
                    "`email` VARCHAR(50) NOT NULL, \n" +
                    "`city` VARCHAR(50) NOT NULL, \n" +
                    "PRIMARY KEY(`podcastHostAccountID`), \n" +
                    "FOREIGN KEY(`podcastHostAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String hosts_table = "CREATE TABLE IF NOT EXISTS `Hosts` ( \n" +
                    "`podcastHostAccountID` VARCHAR(50), \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "PRIMARY KEY(`podcastHostAccountID`, `podcastID`), \n" +
                    "FOREIGN KEY(`podcastHostAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`podcastID`) REFERENCES Podcast(`podcastID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String podcast_episodes_table = "CREATE TABLE IF NOT EXISTS `PodcastEpisode` ( \n" +
                    "`episodeTitle` VARCHAR(100), \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`duration` INT NOT NULL, \n" +
                    "`releaseDate` DATE NOT NULL, \n" +
                    "`listeningCount` INT NOT NULL, \n" +
                    "`advertisementCount` INT NOT NULL, \n" +
                    "PRIMARY KEY(`podcastID`, `episodeTitle`), \n" +
                    "FOREIGN KEY(`podcastID`) REFERENCES Podcast(`podcastID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String podcast_genres_table = "CREATE TABLE IF NOT EXISTS `PodcastGenre` ( \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`genreName` VARCHAR(50), \n" +
                    "PRIMARY KEY(`podcastID`, `genreName`), \n" +
                    "FOREIGN KEY(`podcastID`) REFERENCES Podcast(`podcastID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`genreName`) REFERENCES Genre(`genreName`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String podcast_subscribers_table = "CREATE TABLE IF NOT EXISTS `PodcastSubscribers` ( \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`userAccountID` VARCHAR(50), \n" +
                    "`rating` DECIMAL, \n" +
                    "PRIMARY KEY(`podcastID`, `userAccountID`), \n" +
                    "FOREIGN KEY(`podcastID`) REFERENCES Podcast(`podcastID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`userAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String sponsors_table = "CREATE TABLE IF NOT EXISTS `Sponsor` ( \n" +
                    "`sponsorID` VARCHAR(50), \n" +
                    "`sponsorName` VARCHAR(100) NOT NULL, \n" +
                    "PRIMARY KEY(`sponsorID`) \n" +
                    ");";

            String affiliated_with_table = "CREATE TABLE IF NOT EXISTS `AffiliatedWith` ( \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`sponsorID` VARCHAR(50), \n" +
                    "`amount` DECIMAL(10,2) NOT NULL, \n" +
                    "PRIMARY KEY(`podcastID`, `sponsorID`), \n" +
                    "FOREIGN KEY(`podcastID`) REFERENCES Podcast(`podcastID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`sponsorID`) REFERENCES Sponsor(`sponsorID`) ON \n" +
                    "DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String user_payment_table = "CREATE TABLE IF NOT EXISTS `UserPayment` ( \n" +
                    "`paymentID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "`userAccountID` VARCHAR(50), \n" +
                    "`paymentDate` DATE NOT NULL, \n" +
                    "PRIMARY KEY (`paymentID`), \n" +
                    "FOREIGN KEY (`userAccountID`) REFERENCES \n" +
                    "`User`(`userAccountID`) ON DELETE SET NULL ON UPDATE \n" +
                    "CASCADE \n" +
                    ");";

            String pays_label_table = "CREATE TABLE IF NOT EXISTS `PaysLabel` ( \n" +
                    "`paymentID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "`recordLabelAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`albumID` VARCHAR(50), \n" +
                    "`songTitle` VARCHAR(50), \n" +
                    "`paymentDate` DATE NOT NULL, \n" +
                    "`playCount` INT NOT NULL, \n" +
                    "`amount` DECIMAL(10,2) NOT NULL, \n" +
                    "PRIMARY KEY (`paymentID`), \n" +
                    "FOREIGN KEY (`recordLabelAccountID`) REFERENCES \n" +
                    "`RecordLabel`(`recordLabelAccountID`) ON DELETE CASCADE ON \n" +
                    "UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`albumID`, `songTitle`) REFERENCES \n" +
                    "`Song`(`albumID`, `songTitle`) ON DELETE SET NULL ON UPDATE \n" +
                    "CASCADE \n" +
                    "); ";

            String pays_artist_table = "CREATE TABLE IF NOT EXISTS `PaysArtist` ( \n" +
                    "`paymentID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "`artistAccountID` VARCHAR(50) NOT NULL, \n" +
                    "`recordLabelAccountID` VARCHAR(50), \n" +
                    "`albumID` VARCHAR(50), \n" +
                    "`songTitle` VARCHAR(50), \n" +
                    "`paymentDate` DATE NOT NULL, \n" +
                    "`amount` DECIMAL(10,2) NOT NULL, \n" +
                    "PRIMARY KEY (`paymentID`), \n" +
                    "FOREIGN KEY (`artistAccountID`) REFERENCES \n" +
                    "`Artist`(`artistAccountID`) ON DELETE CASCADE ON UPDATE \n" +
                    "CASCADE, \n" +
                    "FOREIGN KEY (`recordLabelAccountID`) REFERENCES \n" +
                    "`RecordLabel`(`recordLabelAccountID`) ON DELETE SET NULL ON \n" +
                    "UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`albumID`, `songTitle`) REFERENCES \n" +
                    "`Song`(`albumID`, `songTitle`) ON DELETE SET NULL ON UPDATE \n" +
                    "CASCADE \n" +
                    ");";

            String pays_host_table = "CREATE TABLE IF NOT EXISTS `PaysHost` ( \n" +
                    "`paymentID` INT NOT NULL AUTO_INCREMENT, \n" +
                    "`podcastHostAccountID` VARCHAR(50), \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`episodeTitle` VARCHAR(100), \n" +
                    "`paymentDate` DATE NOT NULL, \n" +
                    "`amount` DECIMAL(10,2) NOT NULL, \n" +
                    "PRIMARY KEY(`paymentID`), \n" +
                    "FOREIGN KEY(`podcastID`, `episodeTitle`) REFERENCES \n" +
                    "PodcastEpisode(`podcastID`, `episodeTitle`) ON DELETE SET \n" +
                    "NULL ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`podcastHostAccountID`) REFERENCES \n" +
                    "Account(`accountID`) ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String special_guest_table = "CREATE TABLE IF NOT EXISTS `SpecialGuest` ( \n" +
                    "`guestID` VARCHAR(50), \n" +
                    "`guestName` VARCHAR(100) NOT NULL, \n" +
                    "PRIMARY KEY(`guestID`) \n" +
                    ");";

            String invites_table = "CREATE TABLE IF NOT EXISTS `Invites` ( \n" +
                    "`guestID` VARCHAR(50), \n" +
                    "`podcastID` VARCHAR(50), \n" +
                    "`episodeTitle` VARCHAR(50), \n" +
                    "PRIMARY KEY(`guestID`, `podcastID`, `episodeTitle`), \n" +
                    "FOREIGN KEY(`podcastID`, `episodeTitle`) REFERENCES \n" +
                    "PodcastEpisode(`podcastID`, `episodeTitle`) ON DELETE \n" +
                    "CASCADE ON UPDATE CASCADE, \n" +
                    "FOREIGN KEY(`guestID`) REFERENCES SpecialGuest(`guestID`) \n" +
                    "ON DELETE CASCADE ON UPDATE CASCADE \n" +
                    ");";

            String monthly_revenue_table = "CREATE TABLE IF NOT EXISTS `MonthlyRevenue` (\n" +
                    "`revenueID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`date` DATE NOT NULL,\n" +
                    "`amount` DECIMAL(10,2) NOT NULL,\n" +
                    "PRIMARY KEY(`revenueID`)\n" +
                    ");";

            statement.executeUpdate(accounts_table);
            statement.executeUpdate(genres_table);
            statement.executeUpdate(users_table);
            statement.executeUpdate(record_labels_table);
            statement.executeUpdate(artists_table);
            statement.executeUpdate(albums_table);
            statement.executeUpdate(releases_table);
            statement.executeUpdate(songs_table);
            statement.executeUpdate(song_genres_table);
            statement.executeUpdate(collaborators_table);
            statement.executeUpdate(artist_subscribers_table);
            statement.executeUpdate(podcasts_table);
            statement.executeUpdate(podcast_hosts_table);
            statement.executeUpdate(hosts_table);
            statement.executeUpdate(podcast_episodes_table);
            statement.executeUpdate(podcast_genres_table);
            statement.executeUpdate(podcast_subscribers_table);
            statement.executeUpdate(sponsors_table);
            statement.executeUpdate(affiliated_with_table);
            statement.executeUpdate(user_payment_table);
            statement.executeUpdate(pays_label_table);
            statement.executeUpdate(pays_artist_table);
            statement.executeUpdate(pays_host_table);
            statement.executeUpdate(special_guest_table);
            statement.executeUpdate(invites_table);
            statement.executeUpdate(monthly_revenue_table);

        } catch (SQLException e) {
            connection.rollback();
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private static void loadDataForDemo() throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Account
            statement.executeUpdate(
                    "INSERT INTO Account (accountID, type, password, registrationDate) VALUES " +
                            "('ar2001', 'Artist', '123', '2022-01-01'), " +
                            "('ar2002', 'Artist', '123', '2022-01-01'), " +
                            "('rl3001', 'Label', '123', '2022-01-01'), " +
                            "('rl3002', 'Label', '123', '2022-01-01'), " +
                            "('ph6001', 'Host', '123', '2022-01-01'), " +
                            "('u8001', 'User', '123', '2022-01-01'), " +
                            "('u8002', 'User', '123', '2022-01-01')");

            // Album
            statement.executeUpdate(
                    "INSERT INTO Album (albumID, albumName, releaseYear, edition) VALUES " +
                            "('al4001', 'Electric Oasis', '2022', 'Special'), " +
                            "('al4002', 'Lost in the Echoes', '2021', 'Limited')");

            // Song
            statement.executeUpdate(
                    "INSERT INTO Song (albumID, songTitle, duration, playCount, releaseDate, releaseCountry, language, royaltyRate, trackNumber, mainArtist) VALUES "
                            +
                            "('al4001', 'Electric Dreamscape', 4, 500, '2023-04-01', 'USA', 'English', 0.1, 1, 'ar2001'), "
                            +
                            "('al4001', 'Midnight Mirage', 5, 1000, '2023-04-01', 'USA', 'English', 0.1, 2, 'ar2001'), "
                            +
                            "('al4002', 'Echoes of You', 3, 100, '2023-04-01', 'USA', 'English', 0.1, 1, 'ar2002'), " +
                            "('al4002', 'Rainy Nights', 4, 200, '2023-04-01', 'USA', 'English', 0.1, 2, 'ar2002')");

            // Genre
            statement.executeUpdate(
                    "INSERT INTO Genre (genreName) VALUES " +
                            "('Pop'), ('Thriller'), ('Soothing'), ('Story')," +
                            "('Rap'), ('Rock')," +
                            "('Lifestyle')");

            // RecordLabel
            statement.executeUpdate(
                    "INSERT INTO RecordLabel (recordLabelAccountID, labelName) VALUES " +
                            "('rl3001', 'Elevate Records'), " +
                            "('rl3002', 'Melodic Avenue Music')");

            // Artist
            statement.executeUpdate(
                    "INSERT INTO Artist (artistAccountID, artistName, status, type, country, primaryGenre, monthlyListeners, recordLabelAccountID) VALUES "
                            +
                            "('ar2001', 'Forest', 'active', 'solo', 'USA', 'Pop', 25, 'rl3001'), " +
                            "('ar2002', 'Rain', 'active', 'solo', 'UK', 'Rap', 55, 'rl3002')");

            // Podcast
            statement.executeUpdate(
                    "INSERT INTO Podcast (podcastID, podcastName, language, country, episodeCount, rating, totalSubscribers) VALUES "
                            +
                            "('p5001', 'Mind Over Matter: Exploring the Power of the Human Mind', 'English', 'USA', 5, 4.5, 10)");

            // PodcastHost
            statement.executeUpdate(
                    "INSERT INTO PodcastHost (podcastHostAccountID, firstName, lastName, phone, email, city) VALUES " +
                            "('ph6001', 'Matthew', 'Wilson', '9191231234', 'matthew.wilson@gmail.com', 'Raleigh')");

            // PodcastEpisode
            statement.executeUpdate(
                    "INSERT INTO PodcastEpisode (podcastID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES "
                            + "('p5001', 'The Science of Mindfulness', 32, '2023-01-01', 100, 2), "
                            + "('p5001', 'Unlocking Your Potential', 42, '2023-02-01', 200, 1),"
                            + "('p5001', 'Random name', 45, '2023-02-01', 200, 1),"
                            + "('p5001', 'New ep', 36, '2023-03-01', 250, 2),"
                            + "('p5001', 'good ep', 34, '2023-03-01', 250, 2)");

            // User
            statement.executeUpdate(
                    "INSERT INTO User (userAccountID, firstName, lastName, phone, email, statusOfSubscription) VALUES "
                            + "('u8001', 'Alex', 'A', '9191234567', 'alex.a@gmail.com', 'active'), "
                            + "('u8002', 'John', 'J', '9197654321', 'john.j@gmail.com', 'active')");

            // Releases
            statement.executeUpdate("INSERT INTO Releases (artistAccountID, albumID) VALUES "
                    + "('ar2001', 'al4001'), "
                    + "('ar2002', 'al4002')");

            // SongGenre
            statement.executeUpdate("INSERT INTO SongGenre (genreName, songTitle, albumID) VALUES "
                    + "('Pop', 'Electric Dreamscape', 'al4001'), "
                    + "('Pop', 'Midnight Mirage', 'al4001'), "
                    + "('Rap', 'Echoes of You', 'al4002'), "
                    + "('Rap', 'Rainy Nights', 'al4002')");

            // Collaborators
            statement.executeUpdate("INSERT INTO Collaborators (artistAccountID, songTitle, albumID) VALUES "
                    + "('ar2002', 'Midnight Mirage', 'al4001')");

            // Hosts
            statement.executeUpdate("INSERT INTO Hosts (podcastHostAccountID, podcastID) VALUES "
                    + "('ph6001', 'p5001')");

            // PodcastGenre
            statement.executeUpdate("INSERT INTO PodcastGenre (podcastID, genreName) VALUES "
                    + "('p5001', 'Lifestyle')");

            // PaysLabel
            statement.executeUpdate(
                    "INSERT INTO PaysLabel (songTitle, albumID, recordLabelAccountID, paymentDate, playCount, amount) VALUES "
                            + "('Electric Dreamscape', 'al4001', 'rl3001', '2023-01-01', 10, 0.3), "
                            + "('Electric Dreamscape', 'al4001', 'rl3001', '2023-02-01', 20, 0.6), "
                            + "('Electric Dreamscape', 'al4001', 'rl3001', '2023-03-01', 30, 0.9), "
                            + "('Midnight Mirage', 'al4001', 'rl3001', '2023-01-01', 100, 3), "
                            + "('Midnight Mirage', 'al4001', 'rl3001', '2023-02-01', 200, 6), "
                            + "('Midnight Mirage', 'al4001', 'rl3001', '2023-03-01', 300, 9), "
                            + "('Echoes of You', 'al4002', 'rl3002', '2023-01-01', 1000, 30), "
                            + "('Echoes of You', 'al4002', 'rl3002', '2023-02-01', 2000, 60), "
                            + "('Echoes of You', 'al4002', 'rl3002', '2023-03-01', 3000, 90), "
                            + "('Rainy Nights', 'al4002', 'rl3002', '2023-01-01', 10000, 300), "
                            + "('Rainy Nights', 'al4002', 'rl3002', '2023-02-01', 20000, 600), "
                            + "('Rainy Nights', 'al4002', 'rl3002', '2023-03-01', 30000, 900)");

            // PaysArtist
            statement.executeUpdate("INSERT INTO PaysArtist "
                    + "(artistAccountID, recordLabelAccountID, songTitle, albumID, paymentDate, amount) VALUES "
                    + "('ar2001', 'rl3001', 'Electric Dreamscape', 'al4001', '2023-01-01', 0.7), "
                    + "('ar2001', 'rl3001', 'Electric Dreamscape', 'al4001', '2023-02-01', 1.4), "
                    + "('ar2001', 'rl3001', 'Electric Dreamscape', 'al4001', '2023-03-01', 2.1), "
                    + "('ar2001', 'rl3001', 'Midnight Mirage', 'al4001', '2023-01-01', 3.5), "
                    + "('ar2001', 'rl3001', 'Midnight Mirage', 'al4001', '2023-02-01', 7), "
                    + "('ar2001', 'rl3001', 'Midnight Mirage', 'al4001', '2023-03-01', 10.5), "
                    + "('ar2002', 'rl3002', 'Echoes of You', 'al4002', '2023-01-01', 70), "
                    + "('ar2002', 'rl3002', 'Echoes of You', 'al4002', '2023-02-01', 140), "
                    + "('ar2002', 'rl3002', 'Echoes of You', 'al4002', '2023-03-01', 210), "
                    + "('ar2002', 'rl3002', 'Rainy Nights', 'al4002', '2023-01-01', 700), "
                    + "('ar2002', 'rl3002', 'Rainy Nights', 'al4002', '2023-02-01', 1400), "
                    + "('ar2002', 'rl3002', 'Rainy Nights', 'al4002', '2023-03-01', 2100), "
                    + "('ar2002', 'rl3001', 'Midnight Mirage', 'al4001', '2023-01-01', 3.5), "
                    + "('ar2002', 'rl3001', 'Midnight Mirage', 'al4001', '2023-02-01', 7), "
                    + "('ar2002', 'rl3001', 'Midnight Mirage', 'al4001', '2023-03-01', 10.5)");

            //Pays Host
            statement.executeUpdate("INSERT INTO PaysHost "
                    + "(podcastHostAccountID, podcastID, episodeTitle, paymentDate, amount) VALUES "
                    + "('ph6001', 'p5001', 'The Science of Mindfulness', '2023-01-01', 20),"
                    + "('ph6001', 'p5001', 'Unlocking Your Potential', '2023-02-01', 15),"
                    + "('ph6001', 'p5001', 'Random name', '2023-02-01', 15),"
                    + "('ph6001', 'p5001', 'New ep', '2023-03-01', 20),"
                    + "('ph6001', 'p5001', 'good ep', '2023-03-01', 20);");

            // MonthlyRevenue
            statement.executeUpdate("INSERT INTO MonthlyRevenue (date, amount) VALUES " +
                    "('2023-01-01', 1111), " +
                    "('2023-02-01', 2222), " +
                    "('2023-03-01', 3333), " +
                    "('2023-04-01', 123000)");

            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            connection.rollback();
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static void clearConsoleScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
            // Handle any exceptions.
        }
    }

   private static void prepareDB() {
    try {
		statement.executeUpdate("DROP DATABASE spurush;");
		statement.executeUpdate("CREATE DATABASE spurush;");
		statement.executeUpdate("USE spurush;");
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }

    // initialization steps to setup the database and connections
    private static void initialize() {
        try {
            connectToDatabase();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        Thread.sleep(200);
                        System.out.println("\nShutting down ...");
                        close();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(e.getMessage());
                        System.out.println("Operation Failed. Try Again with Valid Inputs");
                    }
                }
            });
            System.out.println("Connection to WolfMediaDB is successful.");
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }
    }

    // connects to database
    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        String user = "spurush";
        String password = "200476721";
        connection = DriverManager.getConnection(jdbcURL, user, password);
        generateDDLAndDMLStatements();
        statement = connection.createStatement();
    }

    // close connection from database
    private static void close() {
        System.out.println("Closing all connections....");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation Failed. Try Again with Valid Inputs");
            }
        }
    }

    // display table helper
    public static void display_table(ResultSet resultSet) {
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String headers[] = new String[columnsNumber];

            for (int i = 0; i < columnsNumber; i++) {
                headers[i] = rsmd.getColumnName(i + 1);
            }
            setHeaders(headers);
            while (result.next()) {
                String data[] = new String[columnsNumber];
                for (int i = 0; i < columnsNumber; i++) {
                    String temp = result.getString(i + 1);
                    if (resultSet.wasNull()) {
                        data[i] = "NULL";
                    } else {
                        data[i] = temp;
                    }

                }
                addRow(data);
            }
            print();
            resetRows();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Operation Failed. Try Again with Valid Inputs");
        }

    }

    public static void setRightAlign(boolean rightAlign1) {
        rightAlign = rightAlign1;
    }

    public static void setHeaders(String... headers1) {
        headers = headers1;
    }

    public static void addRow(String... cells) {
        rows.add(cells);
    }

    public static void resetRows() {
        rows.clear();
    }

    public static void print() {
        int[] maxWidths = headers != null ? Arrays.stream(headers).mapToInt(String::length).toArray() : null;

        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException(
                        "Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }
        if (headers != null) {
            printLine(maxWidths);
        }
    }

    private static void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }

    private static void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws SQLException {

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("------------------------------Welcome to WolfMediaDB-------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");
        initialize();
        displayAdminMenu();
        close();
    }

    public static void displayAdminMenu() throws SQLException {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome Admin. Below are the operations you can perform.\n");
            System.out.println("1.Show Artist Menu");
            System.out.println("2.Show Song Menu");
            System.out.println("3.Show Album Menu");
            System.out.println("4.Show Record Label Menu");
            System.out.println("5.Show Podcast Menu");
            System.out.println("6.Show Podcast Host Menu");
            System.out.println("7.Show Podcast Episode Menu");
            System.out.println("8.Show User Menu");
            System.out.println("9.Show Account Menu");
            System.out.println("10.Show Reports Menu");
            System.out.println("11.Show Payments Menu");
            System.out.println("12.Show Genres Menu");
            System.out.println("13.Initialize DB");
            System.out.println("0.Exit");

            System.out.print("\n Enter Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Selected Option: Show Artist Menu");
                    showArtistMenu();
                    break;
                case "2":
                    System.out.println("Selected Option: Show Song Menu");
                    showSongMenu();
                    break;
                case "3":
                    System.out.println("Selected Option: Show Album Menu");
                    showAlbumMenu();
                    break;
                case "4":
                    System.out.println("Selected Option: Show Record Label Menu");
                    showRecordLabelMenu();
                    break;
                case "5":
                    System.out.println("Selected Option: Show Podcast Menu");
                    showPodcastMenu();
                    break;
                case "6":
                    System.out.println("Selected Option: Show Podcast Host Menu");
                    showPodcastHostMenu();
                    break;
                case "7":
                    System.out.println("Selected Option: Show Podcast Episode Menu");
                    showPodcastEpisodeMenu();
                    break;
                case "8":
                    System.out.println("Selected Option: Show User Menu");
                    showUserMenu();
                    break;
                case "9":
                    System.out.println("Selected Option: Show Account Menu");
                    showAccountMenu();
                    break;
                case "10":
                    System.out.println("Selected Option: Show Reports Menu");
                    showReportMenu();
                    break;
                case "11":
                    System.out.println("Selected Option: Show Payments Menu");
                    showPaymentsMenu();
                    break;
                case "12":
                    System.out.println("Selected Option: Show Genres Menu");
                    showGenresMenu();
                    break;
                case "13":
                    System.out.println("Selected Option: Initialize DB");
                    prepareDB();
                    initDBTables();
                    loadDataForDemo();
                    break;
                case "0":
                    close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }

}