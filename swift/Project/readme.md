# On Par application usage guide
This application is designed to help golf courses to find gold course near them that matches their needs in things like:
- reviews
- distance
- weather status

The application starts in general view that shows table of all golf courses nearby. The table is sorted in order of the closest courses. The table will display informations per cell such as:
- Name
- distance
- Operational hours(if database contains it)
- Yelp review(if database contains it)
- Picture of the course(if database contains it, otherwise default picture)

When user presses on the desired course, they will navigate to the new view which shows in details the course. It presents information such as:
- Name
- Pictures in sequence each 5 seconds. Or static picture if it is one, if there are no picture the default no picture will show
- hours of operation
- reviews
- Button to open browser and redirect to the golf course's Yelp page
- Table with weather for the week with each cell showing:
    - date
    - summary of the weather
    - wind speed
    - highest temperature
    - lowest temperature

Unfortunately I was unable to finish display of weather table. The application fetches and stores data properly, however, there is a bug in how it displays it, so it is unable to display it. Which is really unfortunate.