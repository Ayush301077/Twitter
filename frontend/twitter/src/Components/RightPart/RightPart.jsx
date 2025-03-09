import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import Brightness4Icon from "@mui/icons-material/Brightness4";
import { Button } from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
const handleChangeTheme = () => {
  console.log("Theme changed");
};
const RightPart = () => {
  return (
    <div className="py-5 sticky top-0 ">
      {/* <div className='relative flex items-center'>
            <input type="text" className='py-3 rounded-full text-gray-500 w-full pl-12' />
            <div className='absoulute top-0 left-0 pl-3 pt3'>
                <SearchIcon className='text-gray-500' />

            </div>
            <Brightness4Icon onClick = {handleChangeTheme} className='text-gray-500 ml-3 cursor-pointer' />
        </div> */}
     <div className='relative flex items-center'>
    <div className='flex items-center bg-gray-200 rounded-full px-3 py-2 w-full'>
        <SearchIcon className='text-gray-500' />
        <input 
            type="text" 
            className='bg-transparent outline-none w-full pl-2 text-gray-700' 
            placeholder='Search' 
        />
    </div>
    <Brightness4Icon 
        onClick={handleChangeTheme} 
        className='ml-3 cursor-pointer' 
    />
</div>

      <section className="my-5">
        <h1 className="text-xl font-bold">Get Verified</h1>
        <h1 className="my-2 font-bold">Subscribe to unlock new features</h1>
        <Button
          variant="contained"
          sx={{ padding: "10px", paddingX: "20px", borderRadius: "25px" }}
        >
          Get Verified
        </Button>
      </section>
      <section className="mt-7 space-y-5">
        <h1 className="font-bold text-xl py-1">What's Happening!!</h1>
        <div>
          <p className="text-sm">FIFA Women's World Cup . LIVE</p>
          <p className="font-bold">India vs Pakistan</p>
        </div>
        {[1, 1, 1, 1].map((item) => (
          <div className="flex justify-between w-full">
            <div>
              <p>Entertainment . Trending</p>
              <p className="font-bold">#One Direction</p>
              <p>34.3K Tweets</p>
            </div>
            <MoreHorizIcon />
          </div>
        ))}
      </section>
    </div>
  );
};

export default RightPart;
