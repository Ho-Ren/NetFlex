use moviedb;

DROP PROCEDURE if exists checkGenre;        -- return id, create new genre if genre doesn't exist
DELIMITER $$
CREATE PROCEDURE checkGenre(
	in genreName varchar(32),
    out idNum int(11))
    Begin
    DECLARE idResult varchar(32);
    SELECT id into idResult
    from genres 
    where genres.name = genreName;
    
    if idResult is null then 
     Select max(id) into idResult
     from genres;
     set idResult = idResult +1;
     insert into genres values (idResult,genreName);
    END IF;
    
    set idNum = idResult;
    END$$
DELIMITER ;

DROP PROCEDURE if exists checkStar;  -- return -1 if star doesn't exist else return star's id #
DELIMITER $$
CREATE PROCEDURE checkStar(
	in firstN varchar(50),
    in lastN varchar (50),
    in dob date,
    out idNum int(11))
    Begin
    DECLARE idResult varchar(32);
    SELECT id into idResult
    from stars 
    where stars.first_name = firstN and stars.last_name=lastN ;
    
    if idResult is null then 
     set idResult = -1;
    END IF;
    
    set idNum = idResult;
    END$$
DELIMITER ;


DROP PROCEDURE if exists checkMovie;   -- return -1 if movie doesn't exit
DELIMITER $$
CREATE PROCEDURE checkMovie(
	in title varchar(50),
    in y year (4),
    out idNum int(11))
    Begin
    DECLARE idResult varchar(32);
    SELECT id into idResult
    from movies 
    where movies.title = title and movies.year=y ;
    
    if idResult is null then 
     set idResult = -1;
    END IF;
    
    set idNum = idResult;
    END$$
DELIMITER ;

DROP PROCEDURE if exists checkStarInMovie;   --  create relationship if there's not one add it
DELIMITER $$
CREATE PROCEDURE checkStarInMovie(
	 in starID int(11),
     in movieID int(11),
     out result int)
    Begin
    DECLARE rowCount int;
    SELECT Count(*) into rowCount
    from stars_in_movies  
    where stars_in_movies.star_id = starID and stars_in_movies.movie_id=movieID;
    set result = 0;
    if rowCount < 0 then 
     insert  into  stars_in_movies values(starID, movieID);
     set result = 1;
    END IF;
    END$$
DELIMITER ;


DROP PROCEDURE if exists checkGenreInMovie;   --  create relationship if there's not one add it
DELIMITER $$
CREATE PROCEDURE checkGenreInMovie(
	 in genreID int(11),
     in movieID int(11),
     out result int)
    Begin
    DECLARE rowCount int;
    SELECT Count(*) into rowCount
    from genres_in_movies  
    where genres_in_movies.genre_id = genreID and genres_in_movies.movie_id=movieID;
    set result = 0;
    if rowCount < 0 then 
     insert  into  genres_in_movies values(genreID, movieID);
     set result = 1;
    END IF;
    END$$
DELIMITER ;


DROP PROCEDURE if exists add_movie;   --  combine all the checking together 
DELIMITER $$
CREATE PROCEDURE add_movie(
	 in firstname varchar(50),
     in lastname varchar(50),
     in dob date,
     in title varchar(50),
     in movieYear year(4),
     in genreName varchar(50),
     out result int)
    Begin
    DECLARE starID int(11);
    DECLARE movieID int(11);
    DECLARE genreID int(11);
    
    call checkStar(firstname, lastname, dob, @starID);
    select @starID into starID;
    call checkMovie(title, movieYear,@movieID);
    select @movieID into movieID;
    
    if starID = -1 then
		set result = -1;                                  -- return -1 if no star, -2 if no movie, 1 add_movie sucess
    elseif movieID = -1 then
		set result = -2;
    else
		call checkGenre(genreName, @genreID);
		select @genreID into genreID;
		call checkStarInMovie(starID,movieID,@out1);
		call checkGenreInMovie(genreID,movieID,@out1);
		set result =1;
    end if;
    end $$
    DELIMITER ;
    
    
    
